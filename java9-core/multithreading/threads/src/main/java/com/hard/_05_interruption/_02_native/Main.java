package com.hard._05_interruption._02_native;

public class Main {
    public static int mValue = 0;

    static Incremenator mInc;

    public static void main(String[] args) {
        mInc = new Incremenator();

        System.out.print("Значение = ");

        mInc.start();

        // Троекратное изменение действия инкременатора
        // с интервалом в i*2 секунд
        for (int i = 1; i <= 3; i++) {
            try {
                Thread.sleep(i * 2 * 1000);        // Ожидание в течении i*2 сек.
            } catch (InterruptedException e) {
            }

            mInc.changeAction();    // Переключение действия
        }

        mInc.interrupt();    // Прерывание побочного потока
    }
}

class Incremenator extends Thread {
    private volatile boolean mIsIncrement = true;

    public void changeAction() {    // Меняет действие на противоположное
        mIsIncrement = !mIsIncrement;
    }

    @Override
    public void run() {
        do {
            if (!Thread.interrupted()) {    //Проверка прерывания
                if (mIsIncrement)
                    Main.mValue++;
                else
                    Main.mValue--;

                //Вывод текущего значения переменной
                System.out.print(Main.mValue + " ");
            } else
                return;        // Завершение потока

            try {
                Thread.sleep(1000);        //Приостановка потока на 1 сек.
            } catch (InterruptedException e) {
                return;    //Завершение потока после прерывания
            }
        }
        while (true);
    }
}
