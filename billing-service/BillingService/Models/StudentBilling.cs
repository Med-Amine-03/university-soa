namespace BillingService.Models;

public class StudentBilling
{
    public int Id { get; set; }
    public string Name { get; set; } = ""; // non-nullable default
    public decimal Amount { get; set; }
}
