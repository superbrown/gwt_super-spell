# Super-Spell UI Design

## Screen Layout

The UI uses absolute positioning to place fixed regions on a full-screen chalkboard background.

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                                                                             │
│  applicationTitle (320% chalk font)          linksInUpperRightCorner        │
│  "Super-Spell" / subject name                  settings                     │
│                                                enter cheat code  cheat sheet│
│  testableListName (150% chalk font)            start over                   │
│  e.g. "Multiplication"                         report problems...           │
│                                                                             │
│  ┌──────────────────────────────────┐        tips                           │
│  │                                  │        "Use Ctrl + to increase        │
│  │  mainPanel (600px wide)          │         the font size..."             │
│  │                                  │                                       │
│  │  (dynamic content area —         │        stayAfterSchoolPosting         │
│  │   chooser panels, test           │        "Stay after school:"           │
│  │   questions, results, MIDI       │          Logan                        │
│  │   player all render here)        │          Peter                        │
│  │                                  │          Hannah                       │
│  │                                  │          ...                          │
│  │                                  │                                       │
│  └──────────────────────────────────┘                                       │
│                                                                             │
│                                                          doodleMessage      │
│                                                  "I like Cheese" (random    │
│                                                   handwritten font)         │
└─────────────────────────────────────────────────────────────────────────────┘
```

### Regions

| Region | Position | Purpose |
|--------|----------|---------|
| applicationTitle | top-left (50px, 10px) | Shows "Super-Spell" initially, then the selected subject |
| testableListName | below title (50px, 60px) | Shows the selected list name (e.g., "Multiplication") |
| mainPanel | left (50px, 110px), 600px wide | All dynamic content — choosers, tests, results |
| linksInUpperRightCorner | top-right (20px from right) | Settings, cheat code, start over, contact links |
| tips | right side (50px from right, 90px down) | Static hint about Ctrl+/- for font size |
| stayAfterSchoolPosting | right side (75px from right, 250px down) | Humorous list of names, grows as user answers |
| doodleMessage | bottom-right (30px from right, 10px from bottom) | Random phrase in a random handwritten font |

## Chalkboard Theme

The entire page is styled to look like a classroom chalkboard:

- Background: solid color (default dark green `#014F2A`)
- Text: chalk-white (`#C9C9C9`) in a crayon/chalk custom font
- Highlights: chalk-yellow (`#C7CB34`) for instructions and celebrations
- Links: muted gray (`#C9C9C9`), underlined

### Board Colors

Nine color schemes, switchable via settings or cheat codes:

| Color | CSS Class | Hex |
|-------|-----------|-----|
| Green (default) | chalkBoardColor | `#014F2A` |
| Brown | brownChalkBoardColor | `#502B01` |
| Blue | blueChalkBoardColor | `#012850` |
| Pink | pinkChalkBoardColor | `#BC005E` |
| Purple | purpleChalkBoardColor | `#48235C` |
| Aqua | aquaChalkBoardColor | `#015050` |
| Orange | orangeChalkBoardColor | `#BA4400` |
| Red | redChalkBoardColor | `#9A001F` |
| Black | blackChalkBoardColor | `#333333` |

### Custom Fonts

| Font | File | Used For |
|------|------|----------|
| crayon_font | CRAYA___.ttf | Primary chalk text (chalkFont class) |
| OPN_StunFillaWenkay | OPN_StunFillaWenkay.ttf | Doodle messages |
| CRAZK | CRAZK.ttf | Doodle messages (no number support) |
| InsertYourNameHere | InsertYourNameHere.ttf | Doodle messages |
| ScratchedCarPaint | ScratchedCarPaint.ttf | Doodle messages (no number support) |

## Navigation Flow

All navigation happens within mainPanel. Each step clears the panel and renders new content.

```
onModuleLoad()
    │
    ├─► / path
    │   │
    │   ▼
    │   SubjectChooserPanel
    │   "What subject do you want?"
    │   [dropdown: 3rd Grade: Spelling, Math Facts, ...]
    │       │
    │       ▼
    │   TestableListChooserPanel
    │   "Which of these do you want to practice?"
    │   [dropdown: Multiplication, Division, ...]
    │       │
    │       ├─► Spelling → SpellingWordSetChooserPanel → TestAdministratorPanel
    │       ├─► Vocabulary → VocabularyWordSetChooserPanel → TestAdministratorPanel
    │       └─► Math Facts → PrimaryOperandChooserPanel → TestAdministratorPanel
    │
    └─► /math path
        │
        ▼
        TestableListChooserPanel (Math Facts only)
        "Which of these do you want to practice?"
        [dropdown: Addition, Subtraction, Multiplication, Division]
            │
            ▼
        PrimaryOperandChooserPanel → TestAdministratorPanel
```

## Testing Behavior

### Question Cycle

The TestAdministratorPanel drives the test loop for all modules:

1. Shuffles the items randomly
2. Presents one question at a time via a TestQuestionPanel
3. User submits answer (button click or Enter key)
4. Evaluates answer → plays sound effect → shows result
5. On correct: advances to next item
6. On incorrect: color flash, then advances (item stays in rotation)
7. When all items completed for a pass, reshuffles remaining items
8. When all items mastered: "Great work!!" message + MIDI player unlocks

### Adaptive Difficulty

Each module's TestingMetric controls question type progression:

| Module | Starts As | On Failure | Completion |
|--------|-----------|------------|------------|
| Spelling | Open-ended | Falls back to multiple-choice, requires 2 successes | Back to open-ended |
| Vocabulary | Multiple-choice | Stays multiple-choice | 2 successes required |
| Math Facts | Open-ended | Stays open-ended, raises required successes to 5 | 1 success (or 5 after failure) |

### Math Facts Timer

Math drills include a countdown timer (default 10 seconds):
- 3-second pre-test countdown before the first question
- Per-question countdown displayed below the question
- On timeout: treated as incorrect, flipping alarm clock animation shown

### Peter Math Mode

A prank mode (toggled via settings checkbox or `petermath` cheat code) that makes addition
problems impossible to get right:

- Only affects **addition** — subtraction, multiplication, and division behave normally
- Every addition answer is marked **incorrect**, regardless of what the user enters
- If the user enters the actual correct answer (e.g., `8` for `3 + 5`), the displayed
  "correct" answer is the two operands concatenated (e.g., `35`) — a nonsensical result
- If the user enters a wrong answer, the real correct answer is shown as usual

The effect is a playful trick: the child keeps getting addition right but is told they're
wrong, with an absurd answer shown as "correct."

## Feedback

### Sound Effects

Three palettes (selectable via settings or cheat codes):
- Three Stooges — random clips on correct/incorrect
- Gomer Pyle — random clips on correct/incorrect
- Silent — no audio feedback

### Visual Feedback

- Correct answer: sound effect plays, advances immediately
- Incorrect answer: board colors flash rapidly through a sequence (BROWN → ORANGE → RED → GREEN → PURPLE), then returns to the user's chosen color
- Completion: blinking "Great work!!" in yellow chalk at 320% font size
- Doodle message and stay-after-school list update every 4 questions

### Encouragement Messages

Random messages displayed on answer:
- Correct: "Yes!", "Great job!", "Rock 'n' Roll!", "F.A.B.!", "You rock!", etc.
- Incorrect: "No.", "You knucklehead!", "Really??", "Whoops!", etc.

## Settings Popup

The settings link opens a SettingsPopupPanel with:
- ☑ Read sample spelling sentences automatically
- ☑ Chalkboard doodling
- ☑ Stay after school list
- ☑ Peter Math mode
- Board color dropdown
- Math question time limit dropdown (2–20 seconds)
- Fun sounds dropdown (None, Gomer Pyle, Three Stooges)

All settings persist in cookies.

## MIDI Player

Unlocked via cheat code (`mmusic`) or after completing all items in a set. Plays classic rock and TV theme MIDI files (Iron Maiden, Styx, Scorpions, Beatles, Brady Bunch, Star Trek, etc.).

## Static Assets

```
gwtapp/war/
├── audioFiles/          # Sound effect MP3s
│   ├── gomerPyle/
│   └── threeStooges/
├── css/SuperSpell.css   # All styles
├── fonts/               # Custom TTF/EOT fonts
├── images/              # Alarm clock GIFs for timeout
└── midiFiles/           # Reward music
    ├── songs/           # Classic rock
    └── tvShows/         # TV themes
```
