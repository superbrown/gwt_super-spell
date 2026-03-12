# Running Super-Spell Locally

This guide shows you how to run the Super-Spell application on your local machine for testing and development.

## Prerequisites

- Java 17 (already installed)
- Maven (already installed)

## Quick Start (Recommended)

### Step 1: Compile the Application

From the project root directory:

```bash
cd /Users/P3221839/Projects/gwt_super-spell
mvn clean package -pl gwtapp
```

This will:
- Compile Java source code
- Compile GWT Java code to JavaScript
- Package everything into the `war` directory

**Note:** GWT compilation can take 30-60 seconds. Be patient!

### Step 2: Run with Jetty

First, kill any process using port 8080:
```bash
lsof -ti :8080 | xargs kill -9 2>/dev/null || true
```

Then start Jetty:
```bash
cd gwtapp
mvn jetty:run
```

### Step 3: Open in Browser

Open your browser to: **http://localhost:8080/**

Press `Ctrl+C` in the terminal to stop the server.

---

## Development Workflow

### Making Changes

1. **Edit Java files** in `gwtapp/src/`
2. **Recompile GWT:**
   ```bash
   cd gwtapp
   mvn gwt:compile
   ```
3. **Refresh browser** to see changes

### Quick Recompile and Run

```bash
cd gwtapp
lsof -ti :8080 | xargs kill -9 2>/dev/null || true
mvn clean gwt:compile jetty:run
```

---

## Alternative: GWT Super Dev Mode (Hot Reload)

For faster development with automatic recompilation:

### Terminal 1: Start Code Server
```bash
cd gwtapp
mvn gwt:codeserver
```

Wait for: "The code server is ready at http://127.0.0.1:9876/"

### Terminal 2: Start Jetty
```bash
cd gwtapp
lsof -ti :8080 | xargs kill -9 2>/dev/null || true
mvn jetty:run
```

### Terminal 3: Open Browser
```
http://localhost:8080/
```

Now when you make changes to Java files, they'll automatically recompile when you refresh the browser!

---

## Troubleshooting

### Port 8080 Already in Use

Kill the process using port 8080:
```bash
lsof -ti :8080 | xargs kill -9
```

Or use a different port:
```bash
mvn jetty:run -Djetty.http.port=9090
```

Then open: http://localhost:9090/

### GWT Compilation Fails

Try cleaning first:
```bash
mvn clean
mvn package -pl gwtapp
```

### Out of Memory During Compilation

Increase Maven memory:
```bash
export MAVEN_OPTS="-Xmx2g"
mvn clean package -pl gwtapp
```

### Changes Not Showing Up

1. Stop Jetty (Ctrl+C)
2. Clean and recompile:
   ```bash
   mvn clean gwt:compile
   ```
3. Restart Jetty:
   ```bash
   lsof -ti :8080 | xargs kill -9 2>/dev/null || true
   mvn jetty:run
   ```
4. Hard refresh browser (Ctrl+Shift+R or Cmd+Shift+R)

### Server-Side Changes (RPC Services)

If you modify server-side code in `gwtapp/src/com/superbrown/superSpell/gwtApp/server/`:

1. Stop Jetty
2. Recompile:
   ```bash
   mvn clean compile
   ```
3. Restart Jetty

---

## What You'll See

When the app loads, you should see:
- Chalkboard-style interface
- "Super-Spell" title at the top
- "waiting for teacher to arrive" message
- Links in upper right: "settings", "enter cheat code", "start over"

### Working Features

✅ **All Core Features Working:**
- Spelling tests with word lists
- Vocabulary tests with definitions
- Math fact drills (addition, subtraction, multiplication, division)
- Text-to-speech "Hear Sentence" feature (Web Speech API)
- MIDI music playback rewards (fixed HTTPS issue)
- Sound effects
- Settings persistence (modern cookie handling)
- Cheat codes and Easter eggs
- Drag-and-drop functionality
- Custom chalkboard fonts

### Known Limitations

⚠️ **Minor Issues:**
- Dictionary.com phonetic scraping feature may not work (if still used)
- Some text files in grade 3, 4, 6, and 7 may need phonetic spellings added (see `.txt` files in `gwtapp/src/.../spellingLists/`)

---

## Testing Features

### Test Spelling Module
1. Select a grade level
2. Choose "Spelling"
3. Select a word list
4. Try the spelling test
5. ✅ Click "Hear Sentence" to test text-to-speech (Web Speech API)

### Test Math Facts
1. Select a grade level
2. Choose "Math Facts"
3. Select operation (addition, multiplication, etc.)
4. Try the timed drills

### Test Vocabulary
1. Select a grade level
2. Choose "Vocabulary"
3. Select a vocabulary list
4. Try the vocabulary test

### Access Cheat Codes
Click "enter cheat code" in the upper right and try:
- `pink` - Change to pink chalkboard
- `blue` - Change to blue chalkboard
- ✅ `music` - Unlock music player (MIDI playback now works!)
- `mastermind` - Play Mastermind game

See `gwtapp/war/cheats/index.html` for full list of cheat codes.

### Test Fixed Features

✅ **Text-to-Speech (Hear Sentence)**
- Click "Hear Sentence" in spelling tests
- Browser's built-in speech synthesis will read the sentence
- Works in Chrome, Firefox, Safari, Edge

✅ **MIDI Music Playback**
- Complete a test to unlock music
- Enter cheat code `music` to access music player
- MIDI files now play correctly (fixed HTTPS issue)
- 100+ songs available in `gwtapp/war/midiFiles/`

✅ **Settings Persistence**
- Change settings (chalkboard color, sound effects, etc.)
- Settings now save correctly with modern cookie handling
- Includes SameSite and Secure attributes for browser compatibility

---

## Production Build

To create a production-ready WAR file:

```bash
mvn clean package
```

Output: `gwtapp/target/gwt-app-1.0.0.war`

Deploy this WAR file to:
- Google App Engine
- Tomcat
- Any Java servlet container

---

## Performance Notes

- **First compilation**: 30-60 seconds (GWT compiles Java to JavaScript)
- **Subsequent compilations**: 20-40 seconds
- **Super Dev Mode**: Much faster (incremental compilation)
- **Jetty startup**: 5-10 seconds

---

## Next Steps

1. **Test the application** - All major features are now working!
2. **Review APP_ENGINE_DEPLOYMENT.md** - Deploy to Google App Engine when ready
3. **Check TODO_BROKEN_FEATURES.md** - See what's been fixed (most critical issues resolved)
4. **Customize word lists** - Add your own spelling/vocabulary lists
5. **Deploy to production** - App is ready for deployment

---

## Need Help?

- Check `QUICKSTART.md` for build commands
- Check `GWT_MAVEN_PLUGIN.md` for GWT details
- Check `TODO_BROKEN_FEATURES.md` for status of fixes (most issues resolved!)
- Check `APPLICATION_DESCRIPTION.md` for feature documentation
- Check `APP_ENGINE_DEPLOYMENT.md` for deployment instructions

---

## Recent Fixes

The following critical issues have been resolved:

✅ **Text-to-Speech** - Implemented Web Speech API (no external dependencies)
✅ **MIDI Playback** - Fixed HTTPS mixed content issue
✅ **Cookie Persistence** - Added SameSite and Secure attributes
✅ **App Engine Deployment** - Migrated to Java 11 Standard Environment
✅ **Legacy Code Cleanup** - Removed browser plugin dependencies

The app is now fully functional and ready for modern browsers!
