package com.superbrown.superSpell.gwtApp.client.spelling;

import com.google.gwt.user.client.ui.*;
import com.superbrown.superSpell.gwtApp.client.SuperSpell;
import com.superbrown.superSpell.gwtApp.client.common.IPanelInitializationListener;
import com.superbrown.superSpell.gwtApp.shared.spelling.SpellingWord;

/**
 */
public abstract class TestQuestionPanel extends com.superbrown.superSpell.gwtApp.client.common.TestQuestionPanel
{
    private HearSentenceLink hearSentenceLink;

    public TestQuestionPanel(SpellingWord spellingWord, IPanelInitializationListener panelInitializationListener)
    {
        super(spellingWord, panelInitializationListener);
    }

    protected Widget createTestQuestionWidget()
    {
        VerticalPanel panel= new VerticalPanel();

        final SpellingWord spellingWord = (SpellingWord) getTestableItem();

        Label phoneticSpellingLabel = new Label(("How do you spell \"" + spellingWord.getPhoneticSpelling() + "\"?"));
        phoneticSpellingLabel.addStyleName("bold");

        Label asInLabel = new Label("As in...");
        asInLabel.addStyleName("bold");

        // Create horizontal panel for sentence with hear link in parentheses
        HorizontalPanel sentencePanel = new HorizontalPanel();
        sentencePanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
        sentencePanel.setSpacing(0);
        
        Label sampleSentenceLabel = new Label((spellingWord.getSampleSentenceToDisplayToUser()));
        sampleSentenceLabel.addStyleName("bold bottomMargin");
        sentencePanel.add(sampleSentenceLabel);
        
        Label space = new Label("\u00A0\u00A0");
        space.addStyleName("bold bottomMargin");
        sentencePanel.add(space);
        
        Label openParen = new Label("(");
        openParen.addStyleName("bold bottomMargin");
        sentencePanel.add(openParen);
        
        createHearSentenceLink(spellingWord);
        hearSentenceLink.addStyleName("bottomMargin");
        sentencePanel.add(hearSentenceLink);
        
        Label closeParen = new Label(")");
        closeParen.addStyleName("bold bottomMargin");
        sentencePanel.add(closeParen);

        VerticalPanel verticalPanel_1 = new VerticalPanel();
        verticalPanel_1.add(phoneticSpellingLabel);
        verticalPanel_1.add(asInLabel);
        verticalPanel_1.add(sentencePanel);
        verticalPanel_1.setWidth("400px");

        panel.add(verticalPanel_1);

        return panel;
    }

    protected HearSentenceLink getHearSentenceLink()
    {
        return hearSentenceLink;
    }

    protected void createHearSentenceLink(SpellingWord spellingWord)
    {
        hearSentenceLink = new HearSentenceLink(spellingWord);
    }


    @Override
    protected void onAttach()
    {
        super.onAttach();
        onAttachAction();
    }

    protected void onAttachAction()
    {
        if (SuperSpell.isInReadImmediatelyMode() && hearSentenceLink != null)
        {
            hearSentenceLink.readNow();
            setToDefaultFocus();
        }
    }
}
