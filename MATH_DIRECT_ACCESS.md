# Math Direct Access - Design Notes

## Overview

This feature allows users to access the Math Facts module directly via the `/math` URL path, bypassing the main subject selection screen. This is useful for users who primarily use the application for math fact drills.

## URLs

- **/** - Main application with subject chooser (Spelling, Vocabulary, Math Facts)
- **/math** - Direct access to Math Facts operation chooser (Addition, Subtraction, Multiplication, Division)

## Architecture

The implementation uses a standard Single Page Application (SPA) routing pattern with server-side 404 redirect support.

### Request Flow

```
User visits /math
        │
        ▼
┌───────────────────┐
│   Web Server      │
│   (Jetty/GAE)     │
│                   │
│  No /math file    │
│  exists → 404     │
└─────────┬─────────┘
          │
          ▼
┌───────────────────┐
│  web.xml 404      │
│  error-page       │
│  redirects to     │
│  /index.html      │
└─────────┬─────────┘
          │
          ▼
┌───────────────────┐
│  index.html       │
│  loads GWT app    │
│  (gwtapp.nocache  │
│   .js)            │
└─────────┬─────────┘
          │
          ▼
┌───────────────────┐
│  SuperSpell.java  │
│  onModuleLoad()   │
│                   │
│  Checks path via  │
│  Window.Location  │
│  .getPath()       │
└─────────┬─────────┘
          │
          ▼
    ┌─────┴─────┐
    │ Path ends │
    │ with      │
    │ "/math"?  │
    └─────┬─────┘
      yes │     no
          │      └──► Normal flow: Subject chooser
          ▼
┌───────────────────┐
│ loadMathModule    │
│ Directly()        │
│                   │
│ Calls RPC to get  │
│ Math Facts list   │
│ and displays      │
│ operation chooser │
└───────────────────┘
```

## Implementation Details

### 1. Server-Side: web.xml

The 404 error page redirect enables client-side routing without creating duplicate HTML files:

```xml
<error-page>
  <error-code>404</error-code>
  <location>/index.html</location>
</error-page>
```

This is a standard SPA pattern. When the server receives a request for `/math`, it doesn't find a physical file, returns a 404, which is then redirected to `/index.html`. The browser URL remains `/math`.

### 2. Client-Side: SuperSpell.java

Path detection occurs in `onModuleLoad()`:

```java
String path = Window.Location.getPath();
mathDirectMode = path.endsWith("/math") || path.endsWith("/math.html");
```

The `mathDirectMode` flag controls routing in `init()`:

```java
if (mathDirectMode) {
    loadMathModuleDirectly();
} else {
    addSchoolClassChooserPanel(this);
}
```

### 3. HTML: index.html

Resource paths use absolute paths to ensure they load correctly regardless of the URL path:

```html
<script src="/gwtapp/gwtapp.nocache.js"></script>
<link href="/css/SuperSpell.css" rel="stylesheet">
```

Previously these were relative paths (`gwtapp/gwtapp.nocache.js`), which would fail when loaded from `/math` because the browser would look for `/math/gwtapp/gwtapp.nocache.js`.

## Design Decisions

### Why 404 Redirect Instead of a Separate HTML File?

Initially, a separate `math.html` file was created, but this introduced code duplication. The 404 redirect approach:

1. **Eliminates duplication** - Single `index.html` serves all routes
2. **Standard pattern** - Common SPA routing technique
3. **Maintainable** - Changes to HTML only need to be made in one place
4. **Extensible** - Easy to add more routes (e.g., `/spelling`, `/vocabulary`) without creating new files

### Why Not a Servlet Filter?

A servlet filter was attempted but caused `ClassNotFoundException` issues with `jakarta.servlet.Filter` due to servlet API version mismatches. The 404 redirect approach avoids these compatibility issues.

## Files Modified

| File | Change |
|------|--------|
| `gwtapp/war/WEB-INF/web.xml` | Added 404 error-page redirect to `/index.html` |
| `gwtapp/war/index.html` | Changed resource paths from relative to absolute |
| `gwtapp/src/.../SuperSpell.java` | Added `mathDirectMode` flag and `loadMathModuleDirectly()` method |
| `README.md` | Documented `/math` URL |
| `RUNNING_LOCALLY.md` | Documented `/math` URL |

## Testing

1. Start the application: `cd gwtapp && mvn jetty:run`
2. Visit `http://localhost:8080/` - Should show subject chooser
3. Visit `http://localhost:8080/math` - Should show Math Facts operation chooser directly
4. The "start over" link from `/math` returns to the subject chooser (normal behavior)

## Future Considerations

This pattern could be extended to support additional direct access paths:

- `/spelling` - Direct to Spelling module
- `/vocabulary` - Direct to Vocabulary module
- `/math/multiplication` - Direct to specific math operation

Each would require:
1. Path detection in `SuperSpell.java`
2. Corresponding routing logic
3. No server-side changes needed (404 redirect handles all paths)
