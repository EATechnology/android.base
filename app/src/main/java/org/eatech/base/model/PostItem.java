/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/7/15 2:43 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.model;

public class PostItem
{
    private int    image;
    private String title;
    private String author;
    private String description;
    private long   date;

    public PostItem(int image, String title, String author, String description, long date)
    {
        this.image = image;
        this.title = title;
        this.author = author;
        this.description = description;
        this.date = date;
    }

    public int getImage()
    {
        return image;
    }

    public void setImage(int image)
    {
        this.image = image;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public long getDate()
    {
        return date;
    }

    public void setDate(long date)
    {
        this.date = date;
    }
}