package com.hard._1_adapter_extends_class._2_composition;

public class Main {
    public static void main(String[] args) {
        IVectorGraphics vectorGraphics = new VectorToRasterAdapter(new RasterGraphics());

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

class VectorToRasterAdapter implements IVectorGraphics {
    private RasterGraphics rasterGraphics;

    public VectorToRasterAdapter(RasterGraphics rasterGraphics) {
        this.rasterGraphics = rasterGraphics;
    }

    @Override
    public void drawVectorShape1() {
        rasterGraphics.drawRasterShape1();
    }

    @Override
    public void drawVectorShape2() {
        rasterGraphics.drawRasterShape2();
    }
}
