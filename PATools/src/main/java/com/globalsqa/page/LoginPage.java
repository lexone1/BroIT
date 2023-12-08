package com.globalsqa.page;

import com.globalsqa.common.annotation.QAComponent;
import com.globalsqa.common.element.ButtonElement;
import com.globalsqa.common.element.PageElement;
import com.globalsqa.common.element.TextElement;

@QAComponent("loginPage")
public class LoginPage extends AbstractPage{

    /**
     * Returns the Username Text Field
     *
     * @return {@link TextElement}
     */
    public TextElement getUsernameTextField()
    {
        return factory.getElement(TextElement.class, by.name("loginPage.usernameTextField"));
    }

    /**
     * Returns the Password Text Field
     *
     * @return {@link TextElement}
     */
    public TextElement getPasswordTextField()
    {
        return factory.getElement(TextElement.class, by.name("loginPage.passwordTextField"));
    }

    /**
     * Returns the Sign In button
     *
     * @return {@link ButtonElement}
     */
    public ButtonElement getSignInButton()
    {
        return factory.getElement(ButtonElement.class, by.xpath("loginPage.signInButton"));
    }

    /**
     * Returns the PATools brand Icon
     *
     * @return {@link PageElement}
     */
    public PageElement getPAToolsBrandIcon()
    {
        return factory.getElement(PageElement.class, by.ID("loginPage.PAToolsBrandIcon"));
    }
}
