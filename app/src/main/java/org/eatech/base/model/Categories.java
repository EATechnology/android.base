/**
 * Created by Elmar <a.k.a. Ramires> Abdurayimov on 5/8/15 6:54 PM
 *
 * @copyright (C)Copyright 2014 e.abdurayimov@gmail.com
 */

package org.eatech.base.model;

import org.eatech.base.R;

import java.util.ArrayList;
import java.util.List;

public class Categories
{
    public static List<CategoryItem> getAll()
    {
        List<CategoryItem> categories = new ArrayList<>();

        categories.add(new CategoryItem(R.drawable.ic_earth, "all", "Все"));

        return categories;
    }

}
