package com.financetracker.serviceimpl;

import com.financetracker.dto.DashboardDto;
import com.financetracker.dto.DashboardResponse;
import com.financetracker.dto.DashboardSummaryResponse;
import com.financetracker.entity.Dashboard;
import com.financetracker.entity.Transaction;
import com.financetracker.entity.TransactionType;
import com.financetracker.entity.User;
import com.financetracker.repo.DashboardRepository;
import com.financetracker.repo.TransactionRepository;
import com.financetracker.repo.UserRepository;
import com.financetracker.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DashboardRepository dashrepo;

    @Override
    public DashboardSummaryResponse getDashboardSummary(Long userId, int month) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Transaction> transactions = transactionRepository.findByUserId(userId);

        double income = 0;
        double expense = 0;
        Map<String, Double> categorySummary = new HashMap<>();

        for (Transaction t : transactions) {
            if (t.getDate().getMonthValue() == month) {

                if (t.getType() == TransactionType.INCOME) {
                    income += t.getAmount();
                } else {
                    expense += t.getAmount();
                }

                categorySummary.merge(t.getCategory(), t.getAmount(), Double::sum);
            }
        }

        double balance = income - expense;

        return new DashboardSummaryResponse(
                income,
                expense,
                balance,
                income,                 // totalBudget
                income - expense,      // remainingBudget
                categorySummary
        );
    }

    // --- ADD SINGLE DASHBOARD ---
    @Override
    public DashboardResponse addTotalIncome(DashboardDto dto) {
        if (dto.getUserId() == null) throw new RuntimeException("UserId cannot be null");

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Dashboard dashboard = new Dashboard();
        dashboard.setUser(user);
        dashboard.setTotalIncome(dto.getTotalIncome());
        dashboard.setTotalExpense(dto.getTotalExpense());

        // Calculate balance and remainingBudget automatically
        double balance = dto.getTotalIncome() - dto.getTotalExpense();
        dashboard.setBalance(balance);

        double totalBudget = dto.getTotalBudget() > 0 ? dto.getTotalBudget() : dto.getTotalIncome();
        dashboard.setTotalBudget(totalBudget);

        dashboard.setRemainingBudget(totalBudget - dto.getTotalExpense());

        dashboard.setCategorySummary(dto.getCategorySummary() != null ? dto.getCategorySummary() : new HashMap<>());

        dashrepo.save(dashboard);

        return new DashboardResponse(
                dashboard.getTotalIncome(),
                dashboard.getTotalExpense(),
                dashboard.getBalance(),
                dashboard.getTotalBudget(),
                dashboard.getRemainingBudget()
        );
    }

    // --- SAVE MULTIPLE DASHBOARDS ---
    @Override
    public List<Dashboard> saveAll(List<DashboardDto> dashboards) {
        List<Dashboard> dashboardEntities = new ArrayList<>();

        for (DashboardDto dto : dashboards) {
            if (dto.getUserId() == null) throw new RuntimeException("UserId cannot be null");

            User user = userRepository.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id " + dto.getUserId()));

            Dashboard dashboard = new Dashboard();
            dashboard.setUser(user);
            dashboard.setTotalIncome(dto.getTotalIncome());
            dashboard.setTotalExpense(dto.getTotalExpense());

            // Calculate balance and remainingBudget
            double balance = dto.getTotalIncome() - dto.getTotalExpense();
            dashboard.setBalance(balance);

            double totalBudget = dto.getTotalBudget() > 0 ? dto.getTotalBudget() : dto.getTotalIncome();
            dashboard.setTotalBudget(totalBudget);
            dashboard.setRemainingBudget(totalBudget - dto.getTotalExpense());

            dashboard.setCategorySummary(dto.getCategorySummary() != null ? dto.getCategorySummary() : new HashMap<>());

            dashboardEntities.add(dashboard);
        }

        return dashrepo.saveAll(dashboardEntities);
    }

    // --- MONTHLY TOTAL INCOME ---
    @Override
    public Dashboard getTotalIncome(Long userId, int month) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double income = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME && t.getDate().getMonthValue() == month)
                .mapToDouble(Transaction::getAmount)
                .sum();

        Dashboard dashboard = new Dashboard();
        dashboard.setTotalIncome(income);
        return dashboard;
    }

    // --- MONTHLY TOTAL EXPENSE ---
    @Override
    public Dashboard getTotalExpense(Long userId, int month) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double expense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE && t.getDate().getMonthValue() == month)
                .mapToDouble(Transaction::getAmount)
                .sum();

        Dashboard dashboard = new Dashboard();
        dashboard.setTotalExpense(expense);
        return dashboard;
    }

    // --- MONTHLY BALANCE ---
    @Override
    public Dashboard getBalance(Long userId, int month) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double income = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME && t.getDate().getMonthValue() == month)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double expense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE && t.getDate().getMonthValue() == month)
                .mapToDouble(Transaction::getAmount)
                .sum();

        Dashboard dashboard = new Dashboard();
        dashboard.setBalance(income - expense);
        return dashboard;
    }

    // --- MONTHLY BUDGET STATUS ---
    @Override
    public Dashboard getBudgetStatus(Long userId, int month) {
        List<Transaction> transactions = transactionRepository.findByUserId(userId);
        double income = transactions.stream()
                .filter(t -> t.getType() == TransactionType.INCOME && t.getDate().getMonthValue() == month)
                .mapToDouble(Transaction::getAmount)
                .sum();

        double expense = transactions.stream()
                .filter(t -> t.getType() == TransactionType.EXPENSE && t.getDate().getMonthValue() == month)
                .mapToDouble(Transaction::getAmount)
                .sum();

        Dashboard dashboard = new Dashboard();
        dashboard.setTotalBudget(income);
        dashboard.setRemainingBudget(income - expense);
        return dashboard;
    }
    
   
}