package br.com.dio.model;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class Board {

    private final List<List<Space>> spaces;

    public Board(List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GameStatusEnum getStatus(){

        if (spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && nonNull(s.getActualValue()))){
            return GameStatusEnum.NON_STARTED;
        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> isNull(s.getActualValue())) ? GameStatusEnum.INCOMPLETE : GameStatusEnum.COMPLETE;
    }

    public boolean hasErrors(){
        if (this.getStatus() == GameStatusEnum.NON_STARTED)
            return false;

        return spaces.stream().flatMap(Collection::stream)
                .anyMatch(s -> nonNull(s.getActualValue()) && !s.getActualValue().equals(s.getExpectedValue()));
    }

    public boolean changeValue(int collumn, int row, int value){
        //collumn vão ser os indices do lista de fora e a interna de <Space> são as linhas
        var space = this.spaces.get(collumn).get(row);
        if (space.isFixed()){
            return false;
        }

        space.setActualValue(value);
        return true;
    }

    public boolean clearValue(int collumn, int row){
        var space = this.spaces.get(collumn).get(row);
        if (space.isFixed()){
            return false;
        }
        space.clearSpace();
        return true;
    }

    public void reset(){
        this.spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameIsFinished(){
        return !hasErrors() && this.getStatus().equals(GameStatusEnum.COMPLETE);
    }
}
