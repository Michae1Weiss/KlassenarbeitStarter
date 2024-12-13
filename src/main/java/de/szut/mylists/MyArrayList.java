package de.szut.mylists;

import java.util.Arrays;

public class MyArrayList {
    private int counter;
    private final int offset = 10;
    private int[] container;

    public MyArrayList() {
        this.counter = 0;
        this.container = new int[10];
    }

    /** Gibt die Anzahl der gespeicherten Zahlen zurück.
     *
     * @return
     */
    int size() {
        return this.counter;
    }

    /** Fügt eine Zahl am Ende der Liste ein.
     *
     * @return
     */
    void add (int value) {
        int idx = this.counter;

        try {
            this.container[idx] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            int oldLength = this.container.length;
            int[] extendedContainer = new int[oldLength + this.offset];
            if (this.counter >= 0) System.arraycopy(this.container, 0, extendedContainer, 0, this.counter);
            this.container = extendedContainer;
            this.container[idx] = value;
        }
        this.counter++;
    }

    /** Gibt den Wert an der Stelle index zurück.
     *
     * @return
     */
    int get(int index) {
        if (index < 0) {
            throw new RuntimeException("Index soll größer als -1 sein!");
        }

        if (this.counter < index) {
            throw new RuntimeException("Index existiert nicht!");
        }

        return this.container[index];
    }

    /** Entfernt die Zahl an der mit index angegeben Stelle.
     * Gibt es den Index nicht, wirft die Methode eine RunTimeException
     * mit der Fehlermeldung „Dieser Index existiert nicht!“.
     * Alle Zahlenwerte rechts vom gelöschten Wert
     * rücken im Array eine Position nach links.
     *
     * @return
     */
    void remove(int index) {
        if (this.counter == 0) {
            throw new RuntimeException("Dieser Index existiert nicht!");
        }

        if (index < 0) {
            throw new RuntimeException("Index soll größer als -1 sein!");
        }

        if (index > this.counter) {
            throw new RuntimeException("Index existiert nicht!");
        }

        int newCounter = 0;
        int[] newContainer = new int[this.container.length];
        for (int i = 0; i < this.counter; i++) {
            if (index == i) {
                continue;
            }
            newContainer[newCounter] = this.container[i];
            newCounter++;
        }
        this.container = newContainer;
        this.counter = newCounter;
    }

    /** Überprüft, ob die Zahl value in der Liste vorhanden ist.
     *
     * @return
     */
    boolean contains(int value) {
        for (int i = 0; i < this.counter; i++) {
            if (this.container[i] == value) {
                return true;
            }
        }
        return false;
    }
}
