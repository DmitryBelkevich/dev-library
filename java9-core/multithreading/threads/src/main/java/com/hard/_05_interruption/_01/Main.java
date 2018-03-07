package com.hard._05_interruption._01;

public class Main {
    public static int mValue = 0;

    static Incremenator mInc;

    public static void main(String[] args) {
        mInc = new Incremenator();

        System.out.print("Значение = ");

        mInc.start();    //Запуск потока

        //Троекратное изменение действия инкременатора
        //с интервалом в i*2 секунд
        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(i * 2 * 1000); //Ожидание в течении i*2 сек.
            } catch (InterruptedException e) {
            }

            mInc.changeAction();    //Переключение действия
        }

        mInc.finish();    //Инициация завершения побочного потока
    }
}

class Incremenator extends Thread {
    private volatile boolean mIsIncrement = true;
    private volatile boolean mFinish = false;

    public void changeAction() {
        mIsIncrement = !mIsIncrement;
    }

    public void finish() {
        mFinish = true;
    }

    @Override
    public void run() {
        do {
            if (!mFinish) {             // Проверка на необходимость завершения
                if (mIsIncrement)
                    Main.mValue++;    //Инкремент
                else
                    Main.mValue--;    //Декремент

                //Вывод текущего значения переменной
                System.out.print(Main.mValue + " ");
            } else
                return;        //Завершение потока

            try {
                Thread.sleep(1000);        //Приостановка потока на 1 сек.
            } catch (InterruptedException e) {
            }
        }
        while (true);
    }
}
