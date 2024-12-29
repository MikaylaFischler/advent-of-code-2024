package Day09;

import Common.Day;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Block> fileList = new ArrayList<>();
    private static final ArrayList<Block> freeList = new ArrayList<>();

    public static void main(String[] args) {
        Day<Long, Long> aocDay = new Day<>(9, "checksum", "checksum");

        Scanner input = aocDay.start();

        ArrayList<Integer> disk = new ArrayList<>();

        int fileID = 0;

        while (input.hasNextLine()) {
            String line = input.nextLine();

            if (!line.isEmpty()) {
                char[] str = line.toCharArray();

                for (int i = 0; i < str.length; i++) {
                    final int entry = str[i] - 0x30;

                    if (i % 2 == 1) {
                        if (entry > 0) {
                            freeList.add(new Block(disk.size(), entry));
                        }

                        for (int addr = 0; addr < entry; addr++) {
                            disk.add(-1);
//                            System.out.print(".");
                        }
                    } else {
                        if (entry > 0) {
                            fileList.add(new Block(disk.size(), entry));
                        }

                        for (int addr = 0; addr < entry; addr++) {
                            disk.add(fileID);
//                            System.out.print(fileID);
                        }

                        fileID++;
                    }
                }

                break;
            }
        }

//        System.out.println();

        ArrayList<Integer> p1Disk = new ArrayList<>(disk);

        for (int i = p1Disk.size() - 1; i >= 0; i--) {
            final int value = p1Disk.get(i);

            if (value >= 0) {
                if (moveToFree(value, i, p1Disk)) {
                    p1Disk.set(i, -1);
                } else {
                    break;
                }
            }
        }

        aocDay.recordPart1(checksum(p1Disk));

        ArrayList<Integer> p2Disk = new ArrayList<>(disk);

        for (int id = fileList.size() - 1; id >= 0; id--) {
            moveBlockToFree(id, fileList.get(id), p2Disk);
        }

        aocDay.recordPart2(checksum(p2Disk));

        aocDay.complete();
    }

    private static boolean moveToFree(int value, int address, ArrayList<Integer> memory) {
        for (int i = 0; i < address; i++) {
            if (memory.get(i) < 0) {
                memory.set(i, value);
                return true;
            }
        }

        return false;
    }

    private static void moveBlockToFree(int id, Block file, ArrayList<Integer> memory) {
        for (int i = 0; i < freeList.size(); i++) {
            final Block freeBlock = freeList.get(i);

            if (freeBlock.size() >= file.size()) {
                System.out.println("moving file " + id);

                for (int i1 = freeBlock.address(); i1 < (freeBlock.address() + file.size()); i1++) {
                    memory.set(i1, id);
                }

                for (int i1 = file.address(); i1 < (file.address() + file.size()); i1++) {
                    memory.set(i1, -1);
                }

                if ((freeBlock.size() - file.size()) > 0) {
                    final Block newBlock = new Block(freeBlock.address() + file.size(), freeBlock.size() - file.size());
                    freeList.set(i, newBlock);
                    System.out.println("resized freelist entry " + i + " : " + freeBlock + " -> " + newBlock);
                } else {
                    freeList.remove(i);
                    System.out.println("removed freelist entry " + i);
                }

                return;
            }
        }

        System.out.println("couldn't move file " + id);
    }

    private static long checksum(ArrayList<Integer> memory) {
        long sum = 0;

        for (int i = 0; i < memory.size(); i++) {
            final int value = memory.get(i);

            if (value >= 0) {
                sum += (long) value * i;
            }

//            System.out.println(i + ":" + value);
        }

        return sum;
    }
}
