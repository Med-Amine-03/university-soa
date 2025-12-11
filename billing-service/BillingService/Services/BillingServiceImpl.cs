using System;
using BillingService.Services;

namespace BillingService.Services
{
    public class BillingServiceImpl : IBillingService
    {
        public int CalculateBill(int studentId, int amount)
        {
            // Realistic billing calculation
            decimal baseTuition = amount;
            decimal taxRate = 0.10m; // 10% tax
            decimal taxAmount = baseTuition * taxRate;
            
            decimal discount = 0;
            if (studentId % 2 == 0) // Example: even IDs get discount
            {
                discount = baseTuition * 0.05m;
            }
            
            decimal totalBill = baseTuition + taxAmount - discount;
            return (int)Math.Round(totalBill);
        }
        public string GetInvoice(int studentId)
        {
            // Generate realistic invoice with date and breakdown
            decimal tuitionAmount = 5000m;
            decimal taxAmount = tuitionAmount * 0.10m;
            decimal discount = (studentId % 2 == 0) ? tuitionAmount * 0.05m : 0;
            decimal totalAmount = tuitionAmount + taxAmount - discount;
            
            return $"Invoice for student #{studentId}:\n" +
                   $"  Tuition: ${tuitionAmount}\n" +
                   $"  Tax (10%): ${taxAmount}\n" +
                   $"  Discount: -${discount}\n" +
                   $"  Total Due: ${totalAmount}\n" +
                   $"  Due Date: {DateTime.Now.AddDays(30):yyyy-MM-dd}";
        }

        public decimal GetStudentBalance(int studentId)
        {
            // Calculate balance based on student ID and simulate payment history
            decimal totalTuition = 5000m;
            decimal tax = totalTuition * 0.10m;
            decimal discount = (studentId % 2 == 0) ? totalTuition * 0.05m : 0;
            decimal fullAmount = totalTuition + tax - discount;
            
            // Simulate partial payments based on student ID
            decimal paidAmount = (studentId % 5) * 1000m;
            decimal balance = fullAmount - paidAmount;
            
            return Math.Max(0, balance);
        }

        public bool PayInvoice(int studentId, decimal amount)
        {
            // Validate payment amount and student ID
            if (studentId <= 0)
                return false;
            
            if (amount <= 0)
                return false;
            
            // Get current balance
            decimal currentBalance = GetStudentBalance(studentId);
            
            // Check if payment amount doesn't exceed balance
            if (amount > currentBalance)
                return false;
            
            // Payment processing successful
            return true;
        }
    }
}
