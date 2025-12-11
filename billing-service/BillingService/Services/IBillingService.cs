using System.ServiceModel;

namespace BillingService.Services
{
    [ServiceContract(Namespace = "http://billing.example.com/")]
    public interface IBillingService
    {
        [OperationContract]
        int CalculateBill(int studentId, int amount);

        [OperationContract]
        string GetInvoice(int studentId);

        [OperationContract]
        decimal GetStudentBalance(int studentId);

        [OperationContract]
        bool PayInvoice(int studentId, decimal amount);
    }
}
