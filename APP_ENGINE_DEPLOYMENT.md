# App Engine Deployment Guide

## App Engine Java 17 Standard Environment

This project is configured to deploy to the App Engine Java 17 Standard Environment using the `appengine-maven-plugin` v2.

### Prerequisites

1. **Google Cloud SDK**: Install the latest gcloud CLI
   ```bash
   curl https://sdk.cloud.google.com | bash
   exec -l $SHELL
   gcloud init
   ```

2. **Java 17**: Ensure you have Java 17 installed
   ```bash
   java -version  # Should show Java 17+
   ```

3. **Maven**: Ensure Maven is installed
   ```bash
   mvn -version
   ```

### Authentication & Permissions

1. **Authenticate with Google Cloud**
   ```bash
   gcloud auth login
   ```

The account is mike.public84@superbrown.com
```
gcloud config set account mike.public84@gmail.com 2>&1
```

2. **Set the active project**
   ```bash
   gcloud config set project super-spell-ver-hrd
   ```

3. **Verify your account has the required roles**

   The deploying account needs at minimum:
   - `App Engine Admin` (roles/appengine.appAdmin)
   - `Storage Admin` (roles/storage.admin) — for uploading artifacts
   - `Cloud Build Editor` (roles/cloudbuild.builds.editor) — if Cloud Build is used

   To check your current roles:
   ```bash
   gcloud projects get-iam-policy super-spell-ver-hrd \
     --flatten="bindings[].members" \
     --filter="bindings.members:$(gcloud config get-value account)" \
     --format="table(bindings.role)"
   ```

   To grant App Engine Admin to an account (requires Owner or IAM Admin):
   ```bash
   gcloud projects add-iam-policy-binding super-spell-ver-hrd \
     --member="user:YOUR_EMAIL@gmail.com" \
     --role="roles/appengine.appAdmin"
   ```

4. **Enable required APIs**
   ```bash
   gcloud services enable appengine.googleapis.com
   gcloud services enable cloudbuild.googleapis.com
   ```

### Deployment Steps

1. **Build the application**
   ```bash
   cd gwtapp
   mvn clean package
   ```

2. **Deploy to App Engine**
   ```bash
   mvn appengine:deploy
   ```

   Or deploy using the gcloud CLI directly:
   ```bash
   gcloud app deploy target/gwt-app-1.0.0.war
   ```

3. **View the application**
   ```bash
   gcloud app browse
   ```

### Configuration Files

- **`gwtapp/src/main/appengine/app.yaml`** — App Engine runtime, scaling, and environment configuration
- **`gwtapp/war/WEB-INF/appengine-web.xml`** — Static file caching, security headers, and session config
- **`gwtapp/pom.xml`** — `appengine-maven-plugin` v2.4.4 configuration

### Troubleshooting

- **"Parameter 'deploy' is unknown"** — The `<configuration>` block in `pom.xml` is using v1 plugin syntax. In v2, properties like `promote` and `stopPreviousVersion` must be direct children of `<configuration>`, not nested under `<deploy>`.
- **`ManagedSdkVerificationException` / exit code 1** — Usually an authentication or permissions issue. Run `gcloud auth login` and verify your account has the App Engine Admin role (see above).
- **Build failures** — Ensure Java 17 is being used for compilation (`java -version`).
- **Runtime errors** — Check App Engine logs:
  ```bash
  gcloud app logs tail -s default
  ```
