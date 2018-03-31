package com.hard._1_adapter_extends_class._1_inheritance;

public class Main {
    public static void main(String[] args) {
        IVectorGraphics vectorGraphics = new VectorToRasterAdapter();

        vectorGraphics.drawVectorShape1();
        vectorGraphics.drawVectorShape2();
    }
}

/**
 * Adaptee
 */

class RasterGraphics {
    public void drawRasterShape1() {
        System.out.println("Raster Shape1");
    }

    public void drawRasterShape2() {
        System.out.println("Raster Shape2");
    }
}

/**
 * Target
 */

interface IVectorGraphics {
    void drawVectorShape1();

    void drawVectorShape2();
}

/**
 * Adapter
 */

class VectorToRasterAdapter extends RasterGraphics implements IVectorGraphics {
    @Override
    public void drawVectorShape1() {
        super.drawRasterShape1();
    }

    @Override
    public void drawVectorShape2() {
        super.drawRasterShape2();
    }
}
