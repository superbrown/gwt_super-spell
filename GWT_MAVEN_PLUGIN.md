# GWT Maven Plugin Guide

## Overview

The GWT Maven Plugin has been added to compile your Java code into optimized JavaScript that runs in the browser.

## What Was Added

### Dependencies
- `gwt-dev` - GWT compiler and development tools

### Plugin Configuration
- **GWT Maven Plugin** (net.ltgt.gwt.maven:gwt-maven-plugin:1.0.1)
  - Compiles Java → JavaScript
  - Generates optimized, obfuscated JavaScript for production
  - Creates browser-specific permutations

## Build Commands

### Compile Java to JavaScript

```bash
# From gwtapp directory
cd gwtapp
mvn gwt:compile

# Or from root directory
cd /Users/P3221839/Projects/gwt_super-spell
mvn gwt:compile -pl gwtapp
```

### Full Build (Java + JavaScript)

```bash
# Compile everything
mvn clean package

# This will:
# 1. Compile Java to .class files
# 2. Compile Java to JavaScript
# 3. Package everything into a .war file
```

### Development Mode (Hot Reload)

```bash
# Run GWT in development mode with code server
cd gwtapp
mvn gwt:codeserver

# Then in another terminal, run the app server
mvn jetty:run
```

## Output

After running `mvn gwt:compile`, you'll find:

```
gwtapp/target/gwt-app-1.0.0/gwtapp/
├── gwtapp.nocache.js          # Entry point (loads correct permutation)
├── *.cache.js                 # Compiled JavaScript (one per browser)
├── *.gwt.rpc                  # RPC policy files
└── gwt/                       # GWT resources
```

## Deployment

### Create WAR file

```bash
mvn clean package

# Output: gwtapp/target/gwt-app-1.0.0.war
```

### Deploy to Tomcat/Jetty

```bash
# Copy WAR to Tomcat
cp gwtapp/target/gwt-app-1.0.0.war /path/to/tomcat/webapps/

# Or run with embedded Jetty
cd gwtapp
mvn jetty:run
```

## Configuration Details

### Module Name
- **Module**: `com.superbrown.superSpell.gwtApp.GwtApp`
- **Rename to**: `gwtapp`
- **Entry Point**: `com.superbrown.superSpell.gwtApp.client.SuperSpell`

### Compilation Settings
- **Style**: OBFUSCATED (minified for production)
- **Source Level**: Java 11
- **Log Level**: INFO

### Change Compilation Style

Edit `gwtapp/pom.xml` and change the `<style>` setting:

```xml
<configuration>
    <!-- Options: OBFUSCATED, PRETTY, DETAILED -->
    <style>PRETTY</style>  <!-- For debugging -->
</configuration>
```

- **OBFUSCATED**: Minified, production-ready (default)
- **PRETTY**: Readable JavaScript for debugging
- **DETAILED**: Most readable, includes comments

## Running the Application

### Option 1: Embedded Jetty (Recommended for Development)

```bash
cd gwtapp
mvn jetty:run
```

Then open: http://localhost:8080/

### Option 2: External Servlet Container

1. Build the WAR:
   ```bash
   mvn clean package
   ```

2. Deploy `gwtapp/target/gwt-app-1.0.0.war` to:
   - Tomcat
   - Jetty
   - Any Java servlet container

### Option 3: GWT Development Mode (Super Dev Mode)

For rapid development with hot reload:

```bash
# Terminal 1: Start code server
cd gwtapp
mvn gwt:codeserver

# Terminal 2: Start app server
mvn jetty:run

# Open browser to: http://localhost:8080/
# Changes to Java code will auto-recompile
```

## Troubleshooting

### Compilation is slow
- GWT compilation can take 15-30 seconds
- Use development mode for faster iteration
- Reduce permutations in .gwt.xml if needed

### JavaScript not updating
```bash
# Clean and rebuild
mvn clean gwt:compile
```

### Out of memory during compilation
Add to `MAVEN_OPTS`:
```bash
export MAVEN_OPTS="-Xmx2g"
mvn gwt:compile
```

## Performance Tips

1. **Use Development Mode** during active development
2. **Compile to JavaScript** only when testing in production mode
3. **Reduce Permutations** in GwtApp.gwt.xml:
   ```xml
   <!-- Limit to specific browsers during development -->
   <set-property name="user.agent" value="safari"/>
   ```

## Next Steps

1. **Add Jetty Plugin** for easy local testing
2. **Configure Super Dev Mode** for hot reload
3. **Optimize GWT module** for faster compilation
4. **Add source maps** for debugging

## Resources

- GWT Documentation: http://www.gwtproject.org/
- GWT Maven Plugin: https://github.com/tbroyer/gwt-maven-plugin
- Maven WAR Plugin: https://maven.apache.org/plugins/maven-war-plugin/
