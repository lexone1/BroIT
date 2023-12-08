package com.globalsqa.page;

import com.globalsqa.common.annotation.Field;
import com.globalsqa.common.annotation.QAComponent;
import com.globalsqa.common.element.ButtonElement;
import com.globalsqa.common.element.LinkElement;
import com.globalsqa.common.element.PageElement;
import com.globalsqa.common.element.TextElement;

@QAComponent("addPlayerPage")
public class AddPlayerPage extends AbstractPage
{
    /**
     * Returns the 'Add' link
     *
     * @return {@link LinkElement}
     */
    public LinkElement getAddLink()
    {
        return factory.getElement(LinkElement.class, by.xpath("addPlayerPage.addLink"));
    }

    /**
     * Returns the 'My Players' link
     *
     * @return {@link LinkElement}
     */
    public LinkElement getMyPlayersLink()
    {
        return factory.getElement(LinkElement.class, by.xpath("addPlayerPage.myPlayersLink"));
    }

    /**
     * Returns the 'My Players' table
     *
     * @return {@link PageElement}
     */
    public PageElement getMyPlayersTable()
    {
        return factory.getElement(PageElement.class, by.xpath("addPlayerPage.myPlayersTable"));
    }

    /**
     * Returns the 'Delete' player icon
     *
     * @return {@link PageElement}
     */
    public PageElement getDeletePlayerIcon()
    {
        return factory.getElementIfPresent(PageElement.class, by.xpath("addPlayerPage.deletePlayerIcon"));
    }

    /**
     * Returns the 'Save' button
     *
     * @return {@link ButtonElement}
     */
    public ButtonElement getSaveButton()
    {
        return factory.getElement(ButtonElement.class, by.xpath("addPlayerPage.saveButton"));
    }

    /**
     * Returns the 'Delete' button
     *
     * @return {@link ButtonElement}
     */
    public ButtonElement getDeleteButton()
    {
        return factory.getElement(ButtonElement.class, by.xpath("addPlayerPage.deleteButton"));
    }

    /**
     * Returns the 'Confirm' button
     *
     * @return {@link ButtonElement}
     */
    public ButtonElement getConfirmButton()
    {
        return factory.getElement(ButtonElement.class, by.xpath("addPlayerPage.confirmButton"));
    }

    /**
     * Returns the 'Add Player' link
     *
     * @return {@link ButtonElement}
     */
    public ButtonElement getAddPlayerButton()
    {
        return factory.getElement(ButtonElement.class, by.xpath("addPlayerPage.addPlayerButton"));
    }

    /**
     * Returns the Tag Text Field
     *
     * @return {@link TextElement}
     */
    @Field(value = "Tag",globalKey = "tag")
    public TextElement getTagTextField()
    {
        return factory.getElement(TextElement.class, by.name("addPlayerPage.tagTextField"));
    }

    /**
     * Returns the ID Text Field
     *
     * @return {@link TextElement}
     */
    @Field(value = "ID",globalKey = "ID")
    public TextElement getIDTextField()
    {
        return factory.getElement(TextElement.class, by.name("addPlayerPage.IDTextField"));
    }

    /**
     * Returns the Account Name Text Field
     *
     * @return {@link TextElement}
     */
    @Field(value = "Account Name",globalKey = "accountName")
    public TextElement getAccountNameTextField()
    {
        return factory.getElement(TextElement.class, by.name("addPlayerPage.accountNameTextField"));
    }
}
