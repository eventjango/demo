package com.example.demo.mediator;

import java.util.Arrays;
import java.util.Vector;

public class TagMediator implements Mediator {

    Vector<Member> members = new Vector<>();

    public TagMediator(Class<TagMediatorSupport> tagMediatorSupport){
        TagMediatorSupport.createMembers(this);
    }

    public TagMediator()
    {}

    @Override
    public void createMembers() {

        Member button = new Button();
        Member textField = new TextField();

        button.setMediator(this);
        textField.setMediator(this);

        members.addAll(Arrays.asList(button, textField));
    }

    @Override
    public void memberChanged() {

        members.stream()
                .forEach(member -> member.setMemberEnabled(false));
    }

    public static class TagMediatorSupport{

        public static void createMembers(TagMediator tagMediator){
            tagMediator.createMembers();
        }
    }
}
