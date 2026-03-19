# Super-Spell Application Design

## Overview

Super-Spell is an elementary school educational web application built with Google Web Toolkit (GWT). It provides interactive practice for Spelling, Vocabulary, and Math Facts, with a classroom chalkboard-themed UI.

**Live:** http://super-spell-ver-hrd.appspot.com

## Architecture

```
┌───────────────────────────────────────────────────────────────┐
│                       Browser (Client)                        │
├───────────────────────────────────────────────────────────────┤
│ ┌───────────────────────────────────────────────────────────┐ │
│ │                 GWT Compiled JavaScript                   │ │
│ │ ┌─────────────┐ ┌─────────────┐ ┌───────────────────────┐ │ │
│ │ │ SuperSpell  │ │  Settings   │ │    Module Panels      │ │ │
│ │ │ (Entry Pt)  │ │  (Cookies)  │ │ Spelling/Vocab/       │ │ │
│ │ │             │ │             │ │ MathFacts/Mastermind  │ │ │
│ │ └──────┬──────┘ └─────────────┘ └───────────┬───────────┘ │ │
│ │        │                                    │             │ │
│ │        └────────────────┬───────────────────┘             │ │
│ │                         │                                 │ │
│ │              ┌──────────▼──────────┐                      │ │
│ │              │ ISuperSpellService  │  (GWT-RPC Async)     │ │
│ │              └──────────┬──────────┘                      │ │
│ └─────────────────────────┼─────────────────────────────────┘ │
└───────────────────────────┼───────────────────────────────────┘
                            │ HTTP/RPC
┌───────────────────────────┼───────────────────────────────────┐
│                 Server (Jetty / App Engine)                   │
├───────────────────────────┼───────────────────────────────────┤
│              ┌────────────▼────────────┐                      │
│              │ SuperSpellServiceImpl   │                      │
│              │ (RemoteServiceServlet)  │                      │
│              └────────────┬────────────┘                      │
│                           │                                   │
│              ┌────────────▼────────────┐                      │
│              │ TestableListLibrarian   │                      │
│              │ (Content Manager)       │                      │
│              └────────────┬────────────┘                      │
│                           │                                   │
│    ┌──────────────────────┼──────────────────────┐            │
│    │                      │                      │            │
│    ▼                      ▼                      ▼            │
│ ┌──────────┐       ┌──────────┐          ┌────────────┐       │
│ │ Spelling │       │Vocabulary│          │ Math Facts │       │
│ │  Lists   │       │  Lists   │          │ (Generated)│       │
│ │  (.txt)  │       │  (Java)  │          │            │       │
│ └──────────┘       └──────────┘          └────────────┘       │
└───────────────────────────────────────────────────────────────┘
```

## Package Structure

```
gwtapp/src/com/superbrown/superSpell/gwtApp/
├── client/                    # GWT client-side code (compiles to JS)
│   ├── SuperSpell.java        # Entry point, routing, cheat codes
│   ├── Settings.java          # Cookie-based preferences, doodle/MIDI lists
│   ├── BoardColor.java        # Enum of 9 chalkboard color schemes
│   ├── SoundPaletteChoice.java # Enum: THREE_STOOOGES, GOMER_PYLE, NONE
│   ├── common/                # Shared UI components
│   │   ├── audio/             # Sound effects, MIDI player
│   │   ├── cheat/             # Cheat code system
│   │   ├── chooserPanels/     # Subject/list selection
│   │   ├── soundPalettes/     # Three Stooges, Gomer Pyle
│   │   ├── timer/             # Math drill timer
│   │   └── TestQuestionPanel.java  # Abstract base for all question types
│   ├── spelling/              # Spelling module UI
│   ├── vocabulary/            # Vocabulary module UI
│   ├── mathFacts/             # Math Facts module UI
│   ├── mastermind/            # Bonus game
│   ├── services/              # RPC service interfaces
│   └── settings/              # Settings popup
├── server/                    # Server-side code
│   ├── SuperSpellServiceImpl.java   # RPC servlet, delegates to TestableListLibrarian
│   ├── TestableListLibrarian.java   # Content registry, lazy math facts init
│   ├── spelling/spellingLists/      # Spelling list .txt files and factories
│   └── vocabulary/vocabularyLists/  # Vocabulary list Java classes by grade
├── shared/                    # Code shared between client & server
│   ├── ITestable.java         # Base interface for testable content
│   ├── ITestableItem.java     # Extends ITestable, adds getCorrectAnswer()
│   ├── TestingMetric.java     # Progress tracking (successes, failures, question type)
│   ├── spelling/              # SpellingWord, SpellingList, misspelling generators
│   ├── vocabulary/            # VocabularyWord, VocabularyList
│   └── mathFacts/             # MathFact, operator/operand lists, operation subpackages
└── GwtApp.gwt.xml             # GWT module: entry point, gwt-dnd, servlet mapping
```

## GWT Code Sharing Model

GWT uses a three-tier architecture where code is organized by where it runs:

| Tier | Package | Runs On | Purpose |
|------|---------|---------|--------|
| Client | `client/` | Browser (JS) | UI, user interaction |
| Server | `server/` | JVM | Data access, business logic |
| Shared | `shared/` | Both | Domain models, interfaces |

Shared code must be GWT-serializable (`IsSerializable`) to cross the RPC boundary.

## Core Interfaces

### ITestable
Base interface for all testable content:
```java
public interface ITestable extends IsSerializable {
    boolean isGotItOnTheFirstTry();
    void reset(boolean force);
    String getName();
}
```

### ITestableItem
Extends ITestable for individual test items:
```java
public interface ITestableItem extends ITestable {
    String getCorrectAnswer();
}
```

### TestingMetric
Tracks progress per item: success/failure counts, required successes, "got it on first try" flag.

## RPC Service

Client-server communication uses GWT-RPC:

```java
@RemoteServiceRelativePath("SuperSpellService")
public interface ISuperSpellService extends RemoteService {
    List<String> getSchoolClassNames();              // Get subjects
    List<String> getTestableListNames(String cls);   // Get lists for subject
    ITestable getTestableList(String cls, String name); // Get content
    void setMathFactTimeLimit(Integer duration);     // (no-op, client-side now)
}
```

Async calls via `ISuperSpellService.App.getInstance()` singleton.

## Data Flow

### 1. Application Startup
```
Browser loads index.html
    │
    ▼
gwtapp.nocache.js loads
    │
    ▼
SuperSpell.onModuleLoad()
    │
    ├─► Check URL path (Window.Location.getPath())
    │       │
    │       ├─► /math → loadMathModuleDirectly()
    │       └─► /     → addSchoolClassChooserPanel()
    │
    ├─► Load settings from cookies
    │
    └─► Initialize sound palettes
```

### 2. Content Selection
```
User selects subject (e.g., "Math Facts")
    │
    ▼
RPC: getTestableListNames("Math Facts")
    │
    ▼
Server: TestableListLibrarian.getTestableListNames()
    │
    ├─► For Math Facts: initMathFacts() creates
    │   Addition, Subtraction, Multiplication, Division
    │
    └─► Returns list names to client
    │
    ▼
User selects specific list (e.g., "Multiplication")
    │
    ▼
RPC: getTestableList("Math Facts", "Multiplication")
    │
    ▼
TestAdministratorPanel displays questions
```

### 3. Testing Flow
```
TestAdministratorPanel
    │
    ├─► Shuffles testable items
    │
    ├─► For each item:
    │       │
    │       ▼
    │   TestQuestionPanel (module-specific)
    │       │
    │       ├─► Display question
    │       ├─► Accept answer
    │       ├─► Update TestingMetric
    │       └─► Play sound effect (correct/incorrect)
    │
    └─► On completion:
            │
            ├─► Flash colors
            └─► Unlock MIDI player (optional)
```

## Educational Modules

### Spelling
- Content: Word lists organized by grade (3rd-7th) and date
- Storage: Text files in `server/spelling/spellingLists/`
- Features:
  - Multiple choice (auto-generated misspellings)
  - Open-ended (type the word)
  - Phonetic syllable display
  - Text-to-speech (Web Speech API)

### Vocabulary
- Content: Word/definition pairs by grade and subject
- Storage: Java classes in `server/vocabulary/vocabularyLists/`
- Features:
  - Multiple choice (select definition)
  - Open-ended (type definition)
  - Social Studies, Science, Word Master lessons

### Math Facts
- Content: Generated programmatically (0-9 operands)
- Operations: Addition, Subtraction, Multiplication, Division
- Features:
  - Timed drills (configurable via cheat code)
  - Operand selection (practice specific numbers)
  - "Peter Math" mode (alternate behavior)

### Mastermind (Bonus)
- Hidden game accessible via cheat code
- Classic code-breaking puzzle

## Settings & Persistence

Settings are stored in browser cookies with 100-year expiration:

| Setting | Cookie Key | Default |
|---------|-----------|---------|
| Board color | `boardColor` | GREEN |
| Sound effects | `soundEffectsSet` | NONE |
| Read immediately | `inReadImmediatelyMode` | true |
| Peter Math | `usingPeterMath` | false |
| Stay after school list | `stayAfterSchoolListEnabled` | true |
| Chalkboard doodling | `chalkboardDoodlingEnabled` | true |
| Math timer (seconds) | `mathQuestionTimerInSeconds` | 10 |

Cookies use modern `SameSite=Lax` attribute via native JavaScript.

## UI Theming

### Chalkboard Colors
9 color schemes: GREEN, BROWN, BLUE, PINK, PURPLE, AQUA, ORANGE, RED, BLACK

### Sound Palettes
- Three Stooges (wowowowo, etc.)
- Gomer Pyle (golly, etc.)
- Silent

### Visual Elements
- Doodle messages (rotating phrases in handwritten fonts)
- "Stay after school" list (humorous name list)
- Custom fonts: OPN_StunFillaWenkay, CRAZK, InsertYourNameHere, ScratchedCarPaint

## Cheat Code System

Cheat codes (entered via "enter cheat code" link):

| Code | Effect |
|------|--------|
| `mmusic`, `mycheatcode` | Unlock MIDI player |
| `pink`, `blue`, `purple`, `brown`, `aqua`, `green`, `orange`, `red`, `black` | Change board color |
| `stooges` | Three Stooges sounds |
| `gomer`, `pyle` | Gomer Pyle sounds |
| `silence` | Disable sounds |
| `mastermind` | Toggle Mastermind game |
| `doodle`, `cheese`, `pizza` | Toggle chalkboard doodling |
| `petermath` | Toggle Peter Math mode |
| `sspell` | Show all misspellings |
| `mathtimer<N>` | Set math timer to N seconds (e.g., `mathtimer5`) |

## Client-Side Routing

The `/math` direct access path uses SPA-style routing:

1. Server returns 404 for `/math` (no physical file)
2. `web.xml` redirects 404 → `/index.html`
3. GWT app detects path via `Window.Location.getPath()`
4. Routes directly to Math Facts module

See [MATH_DIRECT_ACCESS.md](MATH_DIRECT_ACCESS.md) for details.

## Technology Stack

| Component | Technology | Version |
|-----------|------------|---------|
| UI Framework | GWT | 2.10.0 |
| Language | Java | 17 (target), 11 (GWT compile) |
| Build | Maven | 3.x |
| Dev Server | Jetty | 10.0.20 |
| Production | Google App Engine | Java 11 Standard |
| Drag & Drop | gwt-dnd | 3.3.4 |
| Servlet API | Jakarta EE | 5.0 |

## Build & Run

```bash
# Build
mvn clean package

# Run locally
cd gwtapp && mvn jetty:run

# Deploy to App Engine
mvn appengine:deploy
```

## Key Files

| File | Purpose |
|------|---------|
| `SuperSpell.java` | Application entry point, routing, cheat codes |
| `Settings.java` | Cookie-based preferences, MIDI file list |
| `TestableListLibrarian.java` | Content registry, lazy math facts init |
| `SuperSpellServiceImpl.java` | RPC service implementation |
| `GwtApp.gwt.xml` | GWT module config, entry point declaration |
| `web.xml` | Servlet mapping, 404 redirect for SPA routing |
| `index.html` | Host page with absolute resource paths |

## Related Documentation

- [APPLICATION_DESCRIPTION.md](APPLICATION_DESCRIPTION.md) - Feature overview
- [UI_DESIGN.md](UI_DESIGN.md) - UI layout, theming, and navigation behavior
- [MATH_DIRECT_ACCESS.md](MATH_DIRECT_ACCESS.md) - /math URL implementation
- [RUNNING_LOCALLY.md](RUNNING_LOCALLY.md) - Development setup
- [APP_ENGINE_DEPLOYMENT.md](APP_ENGINE_DEPLOYMENT.md) - Production deployment
- [TODO_BROKEN_FEATURES.md](TODO_BROKEN_FEATURES.md) - Modernization status
