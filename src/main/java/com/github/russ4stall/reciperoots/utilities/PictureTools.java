package com.github.russ4stall.reciperoots.utilities;

/**
 * Date: 7/9/13
 * Time: 10:28 AM
 *
 * @author Russ Forstall
 */
public interface PictureTools {

    /**
     *
     * checks to see if an image exists, if not, returns file name for generic pic
     *
     * @param id  recipe id
     *
     * @return an images file name
     */
    String getPicture(int id);
}
