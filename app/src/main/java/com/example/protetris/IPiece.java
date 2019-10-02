package com.example.protetris;

public class IPiece extends Piece {
    private static final int TYPE_BLOCK =  Block.RED_BLOCK;
    public static final int I_S = 4;

    public IPiece(int x, int y) {
        super(x, y);
        initPiece();
        initShadow();
    }

    private void initPiece() {
        nBoard = new int[I_S][I_S];
        sBoard = new int[I_S][I_S];
        for (int c = 0; c < I_S; c++) {
            for (int r = 0; r < I_S; r++) {
                nBoard[c][r] = 0;
                sBoard[c][r] = 0;
            }
        }
        this.nBoard[0][0] = TYPE_BLOCK;
        this.nBoard[0][1] = TYPE_BLOCK;
        this.nBoard[0][2] = TYPE_BLOCK;
        this.nBoard[0][3] = TYPE_BLOCK;
    }

    public void initShadow() {
        copyBoard(nBoard, sBoard, I_S);
        sPos.set(getnPosX(), getnPosY());
        sSety();
    }

    @Override
    public boolean rotatePiece(Board board) {
        int [][] aux = new int[I_S][I_S];
        for (int c = 0; c < I_S; c++) {
            for (int r = 0; r < I_S; r++) {
                aux[c][r] = nBoard[r][3-c];
            }
        }
        if (!touchX(getnPosX(), aux, board) && !touchY(getnPosY(), getnPosX(), aux, board)) {
            nBoard = aux;
            resetShadow(I_S);
            copyBoard(aux, sBoard, I_S);
            sSety();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchX(int x1, int [][] pBoard, Board board) {
        if (x1 >= -2 && x1 < board.NUM_ROW) {
            for (int c = 0; c < this.getS(); c++) {
                for (int r = 0; r < this.getS(); r++) {
                    if (pBoard[c][r] != Block.EMPTY_BLOCK) {
                        if (x1 + c >= Board.NUM_ROW || x1 + c < 0
                        || board.getBoardValue(x1 + c, getnPosY() + r) != Block.EMPTY_BLOCK) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public int getS() {
        return I_S;
    }
}
