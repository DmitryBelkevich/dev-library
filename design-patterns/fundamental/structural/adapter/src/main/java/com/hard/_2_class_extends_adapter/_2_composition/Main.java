package com.hard._2_class_extends_adapter._2_composition;

public class Main {
    public static void main(String[] args) {
        VectorGraphics vectorGraphics = new VectorGraphics(new VectorToRasterAdapter());

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
 * Adapter
 */

class VectorToRasterAdapter extends RasterGraphics {
    public void drawVectorShape1() {
        super.drawRasterShape1();
    }

    public void drawVectorShape2() {
        super.drawRasterShape2();
    }
}

/**
 * Target
 */

class VectorGraphics {
    private VectorToRasterAdapter vectorToRasterAdapter;

    public VectorGraphics(VectorToRasterAdapter vectorToRasterAdapter) {
        this.vectorToRasterAdapter = vectorToRasterAdapter;
    }

    public void drawVectorShape1() {
        vectorToRasterAdapter.drawRasterShape1();
    }

    public void drawVectorShape2() {
        vectorToRasterAdapter.drawRasterShape2();
    }
}
