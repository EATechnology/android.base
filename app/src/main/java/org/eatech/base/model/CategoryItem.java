/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/7/15 2:43 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.model;

public class CategoryItem
{
    private int    icon;
    private String title;
    private String slug;

    public CategoryItem(int icon, String slug, String title)
    {
        this.icon = icon;
        this.slug = slug;
        this.title = title;
    }

    public int getIcon()
    {
        return icon;
    }

    public void setIcon(int icon)
    {
        this.icon = icon;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getSlug()
    {
        return slug;
    }

    public void setSlug(String slug)
    {
        this.slug = slug;
    }
}