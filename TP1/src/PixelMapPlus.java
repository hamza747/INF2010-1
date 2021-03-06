
/**
 * Classe PixelMapPlus Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM Implemente les methodes de
 * ImageOperations
 *
 * @author : Tarek Ould Bachir (remplacer par votre nom) @date : 2011-09-10
 */
public class PixelMapPlus extends PixelMap implements ImageOperations
{
    /**
     * Constructeur creant l'image a partir d'un fichier
     *
     * @param fileName : Nom du fichier image
     */
    PixelMapPlus(String fileName)
    {
        super(fileName);
    }

    /**
     * Constructeur copie
     *
     * @param type  : type de l'image a creer (BW/Gray/Color)
     * @param image : source
     */
    PixelMapPlus(PixelMap image)
    {
        super(image);
    }

    /**
     * Constructeur copie (sert a changer de format)
     *
     * @param type  : type de l'image a creer (BW/Gray/Color)
     * @param image : source
     */
    PixelMapPlus(ImageType type, PixelMap image)
    {
        super(type, image);
    }

    /**
     * Constructeur servant a allouer la memoire de l'image
     *
     * @param type : type d'image (BW/Gray/Color)
     * @param h    : hauteur (height) de l'image
     * @param w    : largeur (width) de l'image
     */
    PixelMapPlus(ImageType type, int h, int w)
    {
        super(type, h, w);
    }

    /**
     * Genere le negatif d'une image
     */
    @Override
    public void negate()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                imageData[i][j] = imageData[i][j].toColorPixel().Negative();
            }
        }
    }

    /**
     * Convertit l'image vers une image en noir et blanc
     */
    @Override
    public void convertToBWImage()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                imageData[i][j] = imageData[i][j].toBWPixel();
            }
        }
    }

    /**
     * Convertit l'image vers un format de tons de gris
     */
    @Override
    public void convertToGrayImage()
    {
        for(int i = 0; i < height; i++)
        {
            for(int j = 0; j < width; j++)
            {
                imageData[i][j] = imageData[i][j].toGrayPixel();
            }
        }
    }

    /**
     * Convertit l'image vers une image en couleurs
     */
    @Override
    public void convertToColorImage()
    {
        // compl�ter
    }

    /**
     * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0) dans
     * le sens des aiguilles d'une montre (clockWise == true) ou dans le sens
     * inverse des aiguilles d'une montre (clockWise == false). Les pixels vides
     * sont blancs.
     *
     * @param clockWise : Direction de la rotation
     */
    @Override
    public void rotate(boolean clockWise)
    {
        double rotate;
        AbstractPixel[][] newImage = new AbstractPixel[height][width];
        
        if(clockWise)
        {
            rotate = Math.PI / 18;
        }
        else
        {
            rotate = Math.PI / 18;
        }
        
        for(int i = 0; i < height; i ++)
        {
            for (int j = 0; j < width; j++)
            {
                int newI = (int) (Math.cos(rotate) * i - Math.sin(rotate) * j);
                int newJ = (int) (Math.sin(rotate) * i + Math.cos(rotate) * j);
                
                if (newI < height && newI >= 0 && newJ < width && newJ >= 0)
                {
                    newImage[newI][newJ] = imageData[i][j];
                }
            }
        }
        imageData = newImage;
    }

    /**
     * Reduit de moitie la longueur et la largeur de l'image
     *
     * @param clockWise : Direction de la rotation
     */
    @Override
    public void halveHW()
    {
        AbstractPixel[][] newImage = new AbstractPixel[height / 2][width / 2];

        for (int i = 0; i < height; i += 2)
        {
            for (int j = 0; j < width; j += 2)
            {
                newImage[i / 2][j / 2] = imageData[i][j];
            }
        }
        height /= 2;
        width /= 2;

        imageData = newImage;
    }

    /**
     * Insert pm dans l'image a la position row0 col0
     */
    @Override
    public void insert(PixelMap pm, int row0, int col0)
    {
        if (pm != null)
        {
            for (int i = row0; i < height && i < pm.height; i++)
            {
                for (int j = col0; j < width && j < pm.width; j++)
                {
                    imageData[i][j] = pm.getPixel(i, j);
                }
            }
        }
    }

    /**
     * Redimentionne l'image
     */
    @Override
    public void resize(int h, int w)
    {
        AbstractPixel[][] newImage = new AbstractPixel[h][w];
        
        for (int i = 0; i < h; i++)
        {
            for (int j = 0; j < w; j++)
            {
                if (i < h && i < height && j < w && j < width)
                {
                    newImage[i][j] = imageData[i][j]; // anciens pixels
                }
                // insert white space
                else
                {
                    newImage[i][j] = new BWPixel(true);
                }
            }
        }
        height = h;
        width = w;
        imageData = newImage;
    }

    /**
     * Effectue une translation de l'image
     */
    @Override
    public void translate(int rowOffset, int colOffset)
    {
        AbstractPixel[][] newImage = new AbstractPixel[height][width];

        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
            {
                if ((i + rowOffset >= 0 && i + rowOffset < height) && (j + colOffset >= 0 && j + colOffset < width))
                {
                    newImage[i + rowOffset][j + colOffset] = imageData[i][j];
                }
                else
                {
                    newImage[i][j] = new BWPixel(true);
                }
            }
        }

        imageData = newImage;
    }
}
