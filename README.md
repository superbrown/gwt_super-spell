# gwt_super-spell

Elementary school educational software I developed in 2010 and 2011 to help my kids study Spelling,
Vocabulary and Math, as well as provide me an opportunity to learn GWT.  I did learn GWT, but best
of all, the software was actually very effective at improving the kids' grades! (We found the math 
fact drills to be particularly effective.)

It's deployed here: http://super-spell-ver-hrd.appspot.com

The UI was intentionally designed to look like a classroom chalkboard. It has some fun stuff in the
settings. Once a level of testing is completed, it allows the kids to play MIDI files of songs
they're familiar with.

I eventually ported the Vocabulary portion of it to Android so the kids could study in the car.
It's available for free at the Play Store:
https://play.google.com/store/apps/details?id=com.superbrown.vocabBlaster 

## Documentation

- **[QUICKSTART.md](QUICKSTART.md)** - Quick start guide for building and running the project
- **[LOCAL_DEPLOYMENT.md](LOCAL_DEPLOYMENT.md)** - Detailed instructions for local development and testing
- **[APPLICATION_DESCRIPTION.md](APPLICATION_DESCRIPTION.md)** - Comprehensive overview of features and architecture
- **[DESIGN.md](DESIGN.md)** - Application design: architecture, data flow, and component details
- **[UI_DESIGN.md](UI_DESIGN.md)** - UI layout, theming, and navigation behavior
- **[MIGRATION.md](MIGRATION.md)** - Maven migration notes from original Ant build system
- **[GWT_MAVEN_PLUGIN.md](GWT_MAVEN_PLUGIN.md)** - Guide to using the GWT Maven plugin
- **[APP_ENGINE_DEPLOYMENT.md](APP_ENGINE_DEPLOYMENT.md)** - Instructions for deploying to Google App Engine
- **[TODO_BROKEN_FEATURES.md](TODO_BROKEN_FEATURES.md)** - Status of modernization efforts and fixes

## Quick Start

```bash
# Build the project
mvn clean package

# Run locally
cd gwtapp
mvn jetty:run

# Open browser to http://localhost:8080
# Or go directly to Math: http://localhost:8080/math
```

See [LOCAL_DEPLOYMENT.md](LOCAL_DEPLOYMENT.md) for detailed instructions.

## Direct Access URLs

The application supports direct access to specific modules:
- **/** - Main application with subject chooser
- **/math** - Direct access to Math Facts module (choose Addition, Subtraction, Multiplication, or Division)

## Recent Modernization

The project has been modernized with:
- ✅ Maven build system (upgraded from Ant)
- ✅ Java 17 (upgraded from Java 1.7)
- ✅ GWT 2.10.0 (upgraded from ~2010 version)
- ✅ App Engine Java 11 Standard Environment
- ✅ Web Speech API for text-to-speech (replaced deprecated Google Translate TTS)
- ✅ HTTPS support for MIDI playback
- ✅ Modern cookie handling with SameSite attributes
- ✅ Removed legacy browser plugin dependencies

See [TODO_BROKEN_FEATURES.md](TODO_BROKEN_FEATURES.md) for complete status.

## Technologies Learned

Things learned (or done) while developing the app: 
- using cookies to store user preferences
- writing code to identify browser type
- incorporating drag and drop functionality
- playing MIDI files in the browser
- temporarily disabling the Enter key to prevent accidental key entry
- reading sentences using Google translate (replaced with Web Speech API)
- playing sound effects
- using custom fonts
- retrieving data from the server
- screen scraping dictionary.com to get phonetic spellings of words
