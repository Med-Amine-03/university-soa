FROM mcr.microsoft.com/dotnet/sdk:6.0 AS build
WORKDIR /src

# Copy project
COPY BillingService/BillingService.csproj ./ 
RUN dotnet restore "BillingService.csproj"

# Copy the rest
COPY BillingService/ .

# Build
RUN dotnet build "BillingService.csproj" -c Release -o /app/build

# Publish
FROM build AS publish
RUN dotnet publish "BillingService.csproj" -c Release -o /app/publish

# Runtime
FROM mcr.microsoft.com/dotnet/aspnet:6.0
WORKDIR /app
COPY --from=publish /app/publish .

# Billing service listens on port 80 INSIDE container
ENV ASPNETCORE_URLS=http://+:80

EXPOSE 80

ENTRYPOINT ["dotnet", "BillingService.dll"]
