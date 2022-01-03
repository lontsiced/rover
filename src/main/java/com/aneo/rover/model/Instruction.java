package com.aneo.rover.model;

import com.aneo.rover.enu.Actions;
import com.aneo.rover.exception.UnknownInstructionException;
import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Instruction {
    private String instruction;

    public Actions[] lectureInstructions(){
        ArrayList<Actions> actions = new ArrayList<Actions>();
        for (char c: instruction.toCharArray()){
            switch (c){
                case 'L': actions.add(Actions.L); break;
                case 'R': actions.add(Actions.R); break;
                case 'M': actions.add(Actions.M); break;
                default: throw new UnknownInstructionException(c);
            }
        }

        return actions.toArray(new Actions[actions.size()]);
    }
}
