package com.binge.smallmvc.threadlocal;

/**
 * @author binge
 * @datetime 2017年9月30日
 * @version
 * @encoding UTF8
 * @Description
 */

public class SequenceC implements Sequence {

    private static MyThreadLocal<Integer> numberContainer = new MyThreadLocal<Integer>() {
        protected Integer initailize() {
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args) {
        final Sequence sequence = new SequenceC();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SequenceC.run(sequence);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SequenceC.run(sequence);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                SequenceC.run(sequence);
            }
        }).start();
    }

    public static void run(Sequence sequence) {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName() + " => " + sequence.getNumber());
        }
    }

}
