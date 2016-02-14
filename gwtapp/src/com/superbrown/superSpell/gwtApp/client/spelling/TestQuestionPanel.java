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

        Label sampleSentenceLabel = new Label((spellingWord.getSampleSentenceToDisplayToUser()));
        sampleSentenceLabel.addStyleName("bold bottomMargin");

        VerticalPanel verticalPanel_1 = new VerticalPanel();
        verticalPanel_1.add(phoneticSpellingLabel);
        verticalPanel_1.add(asInLabel);
        verticalPanel_1.add(sampleSentenceLabel);
        verticalPanel_1.setWidth("400px");

        VerticalPanel verticalPanel_2 = new VerticalPanel();
        verticalPanel_2.addStyleName("leftMargin50");

//        hearSentenceLink = new HearSentenceLink(spellingWord);
//        hearSentenceLink.setText("Hear Sentence");
//        hearSentenceLink.addStyleName("underline");
//        verticalPanel_2.add(hearSentenceLink);
//
//        HTML helpLink = new HTML(
//                "(If the sound doesn't play, an you're using Firefox, " +
//                "<a href=\"http://cafe.elharo.com/privacy/privacy-tip-3-block-referer-headers-in-firefox\" " +
//                "target=\"_blank\">do this</a>.) NOTE: This used Google translate to read the sentences. " +
//                "However, the Google service it used is no longer available, and the one that is available " +
//                "is not free. So this feature no longer works."
//
//        );
//        verticalPanel_2.add(helpLink);

        HorizontalPanel horizontalPanel = new HorizontalPanel();
        horizontalPanel.add(verticalPanel_1);
        horizontalPanel.add(verticalPanel_2);

        panel.add(horizontalPanel);


        return panel;
    }


    @Override
    protected void onAttach()
    {
        super.onAttach();
        onAttachAction();
    }

    protected void onAttachAction()
    {
        if (SuperSpell.isInReadImmediatelyMode())
        {
            hearSentenceLink.readNow();
            setToDefaultFocus();
        }
    }
}
