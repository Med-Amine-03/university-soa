using BillingService.Services;
using SoapCore;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using System.ServiceModel;

var builder = WebApplication.CreateBuilder(args);

// Add SOAP service
builder.Services.AddSoapCore();
builder.Services.AddSingleton<IBillingService, BillingServiceImpl>();

var app = builder.Build();

// Map SOAP endpoint
app.UseRouting();

app.UseEndpoints(endpoints =>
{
    endpoints.UseSoapEndpoint<IBillingService>("/BillingService.svc", new SoapEncoderOptions(), SoapSerializer.XmlSerializer);
});

app.Run();
