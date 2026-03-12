# Super-Spell: Broken Features and Modernization To-Do List

This document identifies features that likely no longer work due to deprecated services, outdated technologies, or modern browser security restrictions, along with suggested approaches for fixing them.

---

## 1. Text-to-Speech (Google Translate TTS) - CRITICAL

**Status:** ❌ Already disabled in code (commented out)

**Issue:**
- The application used Google Translate's free TTS service (`http://translate.google.com/translate_tts`) to read spelling words and sentences
- This service is no longer freely available
- Code is commented out in `HearSentenceLink.java` line 47

**Impact:**
- "Hear Sentence" feature is non-functional
- Students cannot hear words pronounced in context
- Major educational value lost

**Suggested Approaches:**

### Option A: Web Speech API (Recommended - Free)
- Use browser's built-in `SpeechSynthesis` API
- **Pros:** Free, no API keys, works offline, good browser support
- **Cons:** Voice quality varies by browser/OS, limited voice customization
- **Implementation:**
  ```javascript
  // JavaScript JSNI method in GWT
  public native void speak(String text) /*-{
    var utterance = new SpeechSynthesisUtterance(text);
    utterance.lang = 'en-US';
    utterance.rate = 0.9; // Slightly slower for clarity
    $wnd.speechSynthesis.speak(utterance);
  }-*/;
  ```

### Option B: Google Cloud Text-to-Speech API
- Use Google's official paid TTS service
- **Pros:** High quality voices, multiple languages, SSML support
- **Cons:** Requires API key, costs money (but has free tier: 1M chars/month)
- **Cost:** $4 per 1M characters after free tier
- **Implementation:** Server-side API calls, cache audio files

### Option C: Amazon Polly
- AWS text-to-speech service
- **Pros:** Natural sounding voices, good pricing
- **Cons:** Requires AWS account and API key
- **Cost:** $4 per 1M characters
- **Implementation:** Similar to Google Cloud TTS

### Option D: ResponsiveVoice.js (Freemium)
- Third-party JavaScript library
- **Pros:** Easy integration, free tier available
- **Cons:** Free tier has daily limits, requires attribution
- **Implementation:** Include library, simple JavaScript API

**Recommendation:** Start with Web Speech API (Option A) for immediate free solution, then consider Google Cloud TTS if higher quality is needed.

---

## 2. MIDI Playback - HIGH PRIORITY

**Status:** ⚠️ Likely broken in modern browsers

**Issue:**
- Uses external library from `http://www.midijs.net/lib/midi.js` (HTTP, not HTTPS)
- Modern browsers block mixed content (HTTP resources on HTTPS pages)
- MIDI plugin support has been removed from most browsers
- MIDIjs.net may no longer be maintained

**Impact:**
- Music reward system won't work
- Students can't unlock and play MIDI songs after completing tests
- Motivational feature lost

**Suggested Approaches:**

### Option A: MIDI.js Library (Recommended)
- Use modern MIDI.js library (https://github.com/mudcube/MIDI.js/)
- **Pros:** Pure JavaScript, no plugins needed, good browser support
- **Cons:** Requires soundfont files, larger download size
- **Implementation:**
  1. Download MIDI.js library and host locally
  2. Include soundfont files (or use CDN)
  3. Update `SoundWidget_midi_startPlayback.java` to use new API
  4. Update `index.html` to load library from local/HTTPS source

### Option B: Tone.js with MIDI Support
- Modern Web Audio API library with MIDI support
- **Pros:** Very powerful, active development, great audio quality
- **Cons:** More complex API, steeper learning curve
- **Implementation:** Requires significant refactoring of audio code

### Option C: Convert MIDI to MP3/OGG
- Pre-convert all MIDI files to modern audio formats
- **Pros:** Simple, reliable, works everywhere
- **Cons:** Larger file sizes, loses MIDI flexibility
- **Implementation:**
  1. Batch convert all .mid files to .mp3 or .ogg
  2. Update file references in `Settings.java`
  3. Use existing HTML5 audio player code

### Option D: Web MIDI API
- Use browser's native MIDI support
- **Pros:** Native browser support, no external libraries
- **Cons:** Limited browser support, requires MIDI synthesizer
- **Implementation:** Complex, not recommended for this use case

**Recommendation:** Option C (Convert to MP3) for simplicity and reliability, or Option A (MIDI.js) if you want to keep MIDI format.

---

## 3. Mixed Content (HTTP/HTTPS) - HIGH PRIORITY

**Status:** ⚠️ Will cause browser security warnings

**Issue:**
- `index.html` loads MIDIjs library over HTTP: `http://www.midijs.net/lib/midi.js`
- `HearSentenceLink.java` references HTTP Google Translate URL
- Modern browsers block mixed content on HTTPS sites

**Impact:**
- Browser console errors and warnings
- Features may be blocked by browser security
- Poor user experience with security warnings

**Suggested Approaches:**

### Solution: Update All External Resources to HTTPS
1. **MIDI Library:** Host locally or use HTTPS CDN
2. **Remove Google Translate HTTP references** (already disabled)
3. **Audit all external resources:**
   ```bash
   grep -r "http://" --include="*.html" --include="*.java" --include="*.js"
   ```
4. **Update to HTTPS or host locally**

**Implementation:**
- Download and host MIDI.js locally in `war/js/` directory
- Update `index.html` to reference local copy
- Ensure all external resources use HTTPS

---

## 4. Google App Engine Deployment - MEDIUM PRIORITY

**Status:** ⚠️ Using outdated App Engine configuration

**Issue:**
- `appengine-web.xml` uses old App Engine Java 7 format
- References App Engine SDK 1.3.8 (from 2010)
- May not work with modern App Engine Standard Environment
- Version string is hardcoded: "2016-02-21b"

**Impact:**
- Deployment may fail or require migration
- Missing modern App Engine features
- Potential security vulnerabilities in old runtime

**Suggested Approaches:**

### Option A: Migrate to App Engine Java 11+ Standard
- Update to modern App Engine Java runtime
- **Pros:** Better performance, modern Java features, continued support
- **Cons:** Requires configuration changes, testing
- **Implementation:**
  1. Update `appengine-web.xml` to use Java 11 runtime
  2. Add `app.yaml` for modern configuration
  3. Update Maven plugins for App Engine
  4. Test thoroughly

### Option B: Migrate to App Engine Flexible Environment
- Use Docker-based flexible environment
- **Pros:** More control, can use any Java version
- **Cons:** More expensive, more complex
- **Implementation:** Create Dockerfile, update configuration

### Option C: Migrate to Different Platform
- Consider Cloud Run, AWS Elastic Beanstalk, or Heroku
- **Pros:** Modern platform, potentially lower cost
- **Cons:** Requires significant changes, learning curve

**Recommendation:** Option A (App Engine Java 11 Standard) for minimal changes and continued Google Cloud integration.

---

## 5. Cookie SameSite Attribute - MEDIUM PRIORITY

**Status:** ⚠️ May cause issues in modern browsers

**Issue:**
- `Settings.java` uses GWT's `Cookies.setCookie()` without SameSite attribute
- Modern browsers require SameSite attribute for cookies
- Chrome, Firefox, Safari now default to `SameSite=Lax`

**Impact:**
- User preferences may not persist correctly
- Cookie warnings in browser console
- Settings may reset unexpectedly

**Suggested Approaches:**

### Solution: Add SameSite Attribute to Cookies
- GWT's Cookies API may not support SameSite directly
- **Implementation:**
  1. Create custom cookie setter using JSNI:
     ```java
     public static native void setCookieWithSameSite(String name, String value, Date expires) /*-{
       var expiresStr = expires.toUTCString();
       $doc.cookie = name + "=" + value + 
                     "; expires=" + expiresStr + 
                     "; path=/; SameSite=Lax; Secure";
     }-*/;
     ```
  2. Update `Settings.java` to use new method
  3. Add `Secure` flag if serving over HTTPS (recommended)

**Note:** This requires serving the app over HTTPS for `Secure` flag to work.

---

## 6. Browser Plugin Dependencies - LOW PRIORITY

**Status:** ⚠️ Fallback audio methods may not work

**Issue:**
- Code includes fallback to `<embed>` and `<object>` tags for audio
- These relied on browser plugins (Flash, QuickTime, etc.)
- Modern browsers have removed plugin support

**Impact:**
- Fallback audio methods won't work
- Only HTML5 `<audio>` tag will work
- Code complexity without benefit

**Suggested Approaches:**

### Solution: Remove Legacy Audio Code
1. **Remove unused classes:**
   - `SoundWidget_embedTag.java`
   - `SoundWidget_objectTag.java`
2. **Keep only:**
   - `SoundWidget_audioTag.java` (HTML5 audio)
3. **Simplify audio architecture**
4. **Test with modern browsers only**

**Implementation:**
- Delete obsolete classes
- Update factory/selection logic to only use HTML5 audio
- Remove browser detection code for plugins

---

## 7. Dictionary.com Screen Scraping - LOW PRIORITY

**Status:** ❓ Unknown if still used

**Issue:**
- README mentions "screen scraping dictionary.com to get phonetic spellings"
- No code found in current search, may have been removed
- Screen scraping is fragile and may break with website changes

**Impact:**
- If still used, phonetic spellings may not load
- Potential legal/ToS issues with scraping

**Suggested Approaches:**

### Option A: Use Dictionary API
- Use official dictionary API (e.g., Merriam-Webster, Oxford)
- **Pros:** Reliable, legal, structured data
- **Cons:** Requires API key, may have costs
- **Implementation:** Replace scraping with API calls

### Option B: Pre-generate Phonetic Data
- Generate phonetic spellings once and store in database/files
- **Pros:** Fast, no external dependencies
- **Cons:** Can't handle new words dynamically
- **Implementation:** One-time data generation script

**Recommendation:** Verify if this feature is still in use. If so, use Option B (pre-generate data) for simplicity.

---

## 8. GWT Version Compatibility - MEDIUM PRIORITY

**Status:** ⚠️ Using GWT 2.10.0 (latest, but limited Java support)

**Issue:**
- GWT 2.10.0 only supports Java 11 source level
- Project is configured for Java 17
- Some Java 17 features won't work in GWT client code

**Impact:**
- Potential compilation issues
- Can't use newer Java features in client code
- Limited by GWT's Java support

**Suggested Approaches:**

### Option A: Accept GWT Limitations
- Keep Java 17 for server code
- Use Java 11 features only in client code
- **Pros:** Simple, works with current GWT
- **Cons:** Can't use modern Java features in UI

### Option B: Migrate to Modern Framework
- Consider migrating to React, Vue, or Angular
- **Pros:** Modern tooling, better browser support, active community
- **Cons:** Complete rewrite, significant effort
- **Implementation:** Gradual migration, start with new features

**Recommendation:** Option A for now, consider Option B for long-term modernization.

---

## 9. Ant Build Files - LOW PRIORITY

**Status:** ⚠️ Legacy build files present

**Issue:**
- Old Ant build files still present (`module_spellingwordlistsetup.xml`)
- Reference App Engine SDK 1.3.8 from 2010
- May confuse developers

**Impact:**
- Confusion about build system
- Outdated documentation
- No functional impact (Maven is used)

**Suggested Approaches:**

### Solution: Clean Up Legacy Files
1. **Move to archive directory:**
   ```bash
   mkdir -p archive/legacy-build
   mv SpellingWordListSetup/*.xml archive/legacy-build/
   ```
2. **Update documentation** to reference Maven only
3. **Add note in README** about legacy files

---

## Priority Summary

### Critical (Fix First)
1. ✅ **Text-to-Speech** - Core educational feature
2. ✅ **MIDI Playback** - Motivational reward system

### High Priority (Fix Soon)
3. ✅ **Mixed Content (HTTP/HTTPS)** - Security and functionality
4. ✅ **Google App Engine Deployment** - Platform compatibility

### Medium Priority (Plan to Fix)
5. ✅ **Cookie SameSite** - User experience
6. ✅ **GWT Version Compatibility** - Long-term maintainability

### Low Priority (Nice to Have)
7. ✅ **Browser Plugin Dependencies** - Code cleanup
8. ✅ **Dictionary.com Scraping** - Verify if needed
9. ✅ **Ant Build Files** - Documentation cleanup

---

## Recommended Action Plan

### Phase 1: Quick Wins (1-2 days)
1. Implement Web Speech API for text-to-speech
2. Convert MIDI files to MP3 format
3. Update index.html to remove HTTP references
4. Add SameSite attribute to cookies

### Phase 2: Platform Updates (3-5 days)
1. Update App Engine configuration to Java 11
2. Test deployment on modern App Engine
3. Update documentation

### Phase 3: Code Cleanup (2-3 days)
1. Remove legacy audio code (embed/object tags)
2. Archive old Ant build files
3. Update README with current status

### Phase 4: Long-term Improvements (Future)
1. Consider framework migration (React/Vue/Angular)
2. Implement proper dictionary API
3. Modernize UI/UX

---

## Testing Checklist

After implementing fixes, test:
- [ ] Text-to-speech works in Chrome, Firefox, Safari
- [ ] MIDI/MP3 playback works in all browsers
- [ ] No mixed content warnings in browser console
- [ ] Cookies persist across sessions
- [ ] App deploys successfully to App Engine
- [ ] All spelling/vocabulary/math features work
- [ ] Sound effects play correctly
- [ ] Settings save and load properly
- [ ] Mobile browser compatibility

---

## Notes

- The app is currently deployed at http://super-spell-ver-hrd.appspot.com
- Test changes in a staging environment before updating production
- Consider creating a "v2" version for major changes
- Keep backward compatibility for existing users' saved preferences
