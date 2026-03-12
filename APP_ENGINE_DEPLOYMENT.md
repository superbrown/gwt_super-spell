# App Engine Deployment Guide

## Modern App Engine Java 11 Standard Environment

This project has been updated to use the modern App Engine Java 11 Standard Environment.

### Prerequisites

1. **Google Cloud SDK**: Install the latest gcloud CLI
   ```bash
   # Install gcloud CLI (if not already installed)
   curl https://sdk.cloud.google.com | bash
   exec -l $SHELL
   gcloud init
   ```

2. **Java 11+**: Ensure you have Java 11 or later installed
   ```bash
   java -version  # Should show Java 11+
   ```

3. **Maven**: Ensure Maven is installed
   ```bash
   mvn -version
   ```

### Deployment Steps

1. **Set up Google Cloud Project**
   ```bash
   # Set your project ID (replace with your actual project ID)
   gcloud config set project super-spell-ver-hrd
   
   # Enable required APIs
   gcloud services enable appengine.googleapis.com
   ```

2. **Build the Application**
   ```bash
   cd gwtapp
   mvn clean compile gwt:compile package
   ```

3. **Deploy to App Engine**
   ```bash
   # Deploy using the App Engine Maven plugin
   mvn appengine:deploy
   
   # Or deploy using gcloud CLI directly
   gcloud app deploy target/gwt-app-1.0.0.war
   ```

4. **View the Application**
   ```bash
   gcloud app browse
   ```

### Configuration Files

- **`appengine-web.xml`**: Modern App Engine configuration with Java 11 runtime
- **`app.yaml`**: Additional App Engine settings (optional, complements appengine-web.xml)
- **`pom.xml`**: Updated with modern App Engine Maven plugin

### Key Improvements

1. **Java 11 Runtime**: Modern, supported runtime environment
2. **Automatic Scaling**: Efficient resource usage with 0-10 instances
3. **Improved Caching**: Better cache headers for GWT files and static assets
4. **Security Headers**: Modern security headers (X-Content-Type-Options, X-Frame-Options, etc.)
5. **Health Checks**: Proper readiness and liveness checks
6. **Resource Limits**: Appropriate CPU and memory allocation

### Local Development

Run locally using Jetty:
```bash
mvn jetty:run
```

Then open http://localhost:8080

### Troubleshooting

1. **Build Issues**: Ensure Java 11 is used for compilation
2. **Deployment Issues**: Check that gcloud is authenticated and project is set
3. **Runtime Issues**: Check App Engine logs in Google Cloud Console

### Migration Notes

- Removed hardcoded application ID and version from appengine-web.xml
- These are now managed via gcloud CLI configuration
- Sessions are enabled for user preferences (cookies)
- Static file caching optimized for GWT application structure