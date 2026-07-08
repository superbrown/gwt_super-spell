# Super-Spell User's Guide

A guide to actually using Super-Spell — for parents setting it up and kids using it to
practice Spelling, Vocabulary, and Math Facts. (For build/deploy instructions, see
[README.md](README.md) and the other docs it links to.)

## Opening the App

- Live site: http://super-spell-ver-hrd.appspot.com
- Running it locally: http://localhost:8080/ (see [LOCAL_DEPLOYMENT.md](LOCAL_DEPLOYMENT.md))

When it first loads you'll see "waiting for teacher to arrive" — that clears once you
make your first selection.

## Choosing What to Study

1. **"What subject do you want?"** — pick a grade + subject combination, for example:
   - `2nd Grade: Spelling`, `3rd Grade: Spelling`, `4th Grade: Spelling`,
     `5th Grade: Spelling`, `6th Grade: Spelling`
   - `3rd Grade: Vocabulary`, `3rd Grade: Social Studies`, `6th Grade: Vocabulary`,
     `7th Grade: Vocabulary`, `7th Grade: Spelling of Vocabulary Words`
   - `Math Facts`
2. **Pick a specific list** within that grade/subject — a dated spelling list, a
   vocabulary lesson/unit, or for Math Facts, an operation (Addition, Subtraction,
   Multiplication, Division). Math Facts lists show the current time limit in their
   name, e.g. "Addition (10 second time limit)".

### Jumping straight to Math

Going to `/math` instead of the site root skips the subject chooser entirely and
takes you straight to picking a Math Facts operation.

## How a Test Works

The question format and pass/fail rule differ by subject:

- **Spelling** — starts as a **typed-answer** question. Get it right first try and
  it's done. Get it wrong, and it becomes **multiple-choice** for a couple of rounds
  of practice (two correct picks in a row needed), then switches back to one more
  typed-answer question to confirm you've really got it before it's marked complete.
- **Vocabulary** — always **multiple-choice** (pick the correct definition, or pick
  the correct word for a definition). No typed-answer step; you need two correct
  picks to complete a word.
- **Math Facts** — always **typed-answer**. Get a fact wrong and it doesn't switch to
  multiple-choice — instead you need five correct answers in a row (instead of just
  one) before it's marked complete.

Whatever the subject, the list keeps cycling through whatever items aren't finished
yet, in a new random order each pass, until everything has been answered correctly.

Press **Enter** or click the answer button to submit.

### Helpful features while testing

- **Hear Sentence** (spelling tests) — reads the example sentence aloud using your
  browser's built-in text-to-speech.
- Immediate feedback after each answer, with the correct spelling/answer highlighted
  if you got it wrong.

## The Links in the Upper-Right Corner

- **settings** — opens the preferences popup (see below)
- **enter cheat code** — type in one of the codes listed further down
- **start over** — returns to the subject chooser

## Settings

| Setting | What it does |
|---|---|
| read sample spelling sentences automatically | Reads each sentence aloud without needing to click "Hear Sentence" |
| chalkboard doodling | Toggles the decorative handwritten "doodle" messages |
| stay after school list | Toggles a running (just-for-fun) list of names on the chalkboard |
| execute addition math facts in "Peter mode" | A prank mode for the Addition operation only — see "Peter Mode" below |
| board color | 9 chalkboard color themes: Green, Aqua, Black, Blue, Brown, Orange, Pink, Purple, Red |
| math question time limit | 2–20 seconds per question; requires starting the list over to take effect |
| fun sounds | Sound effect palette for right/wrong answers: None, Gomer Pyle, or Three Stooges |

## Cheat Codes

Click **"enter cheat code"** and type one of these (all lowercase):

| Code | Effect |
|---|---|
| `pink`, `blue`, `purple`, `brown`, `aqua`, `green`, `orange`, `red`, `black` | Changes the chalkboard color |
| `stooges` | Three Stooges sound effects |
| `gomer` or `pyle` | Gomer Pyle sound effects |
| `silence` | Mutes all sound |
| `doodle`, `cheese`, or `pizza` | Turns on chalkboard doodling |
| `mastermind` | Opens the hidden Mastermind code-breaking game |
| `petermath` | Turns on "Peter Mode" — a prank affecting only the Addition operation, see below |
| `mmusic` or `mycheatcode` | Toggles the MIDI music player on/off |
| `sspell` | Switches spelling-list selection into preview mode: picking a list shows every spelling combination generated for each word instead of starting a test |
| `mathtimer` followed by a number, e.g. `mathtimer45` | Sets the math question timer to an exact number of seconds — including values outside the 2–20 second range offered in Settings |

Entering an unrecognized code is harmless — it's just ignored.

## Bonus: Mastermind

Enter the `mastermind` cheat code to open a classic code-breaking guessing game — a
fun break from studying.

## Peter Mode

`petermath` (or the matching Settings checkbox) turns on a prank that affects
**only the Addition operation** in Math Facts — Subtraction, Multiplication, and
Division are unaffected. While it's on, every addition answer is marked wrong, no
matter what you type. The twist is in what "correct answer" gets displayed afterward:
if you actually typed the right sum, it shows the two numbers stuck together instead
(e.g. `3 + 5` shows `35` as "correct") — a nonsense answer. If you actually typed the
wrong sum, it shows the real correct answer as normal. Net effect: the child keeps
getting addition right but is told they're wrong, with an absurd "correct" answer
shown instead.

## The Reward

Getting every word/fact in an entire list right on your very first try (no mistakes at
all) is the app's actual goal, not just "finishing" the list — a "Great work!!"
celebration appears, and the MIDI music player unlocks automatically, letting you play
from a library of familiar songs. This is the app's built-in motivation to aim for
first-try accuracy rather than just grinding through retries.

This is a literal, strict requirement, not just a figure of speech: a list is broken
into smaller practice sets, and each one only counts toward the reward if you clear it
without a single wrong answer. Make even one mistake in a set, and that set's progress
is wiped the next time you return to the list-selection screen — there's no partial
credit carried over, so re-attempts have to be clean runs too. Once every set in the
list shows "(PASSED)," all of them cleared without a miss, the celebration and music
unlock fire together.

There's also a `mmusic` (or `mycheatcode`) cheat code that toggles the music player
open/closed at any time — but it's a parent-level override, not something advertised
to kids: it's deliberately left off the in-app cheat sheet (`cheats/index.html`), so
using the earned path is what a kid discovers by playing normally. If you want your
child to experience it as a genuine reward, don't share this code with them.

## Tips

- Spelling and Vocabulary lists are organized by grade, and within a grade, by date
  or lesson/unit — so you can pick out a specific week's assignment.
- If you're not sure what a setting or cheat code does and want to double-check
  current behavior against the code, see `CheatCodes.java` and `SettingsPopupPanel.java`
  in `gwtapp/src/.../client/`.
