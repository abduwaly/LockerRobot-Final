package com.tw.locker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class Locker {

    private final String id;
    private final LockerType type;
    private final int capacity;


    private List<Bag> bags = new ArrayList<>();

    public double vacancyRate(){
        return (double)(this.capacity - this.getBags().size()) / this.capacity;
    }

    public Ticket saveBag(Bag bag) {
        if(this.capacity <= 0){
            throw new NoStorageException();
        } else {
            this.bags.add(bag);
            return new Ticket(UUID.randomUUID(), bag.getId(), this.id);
        }
    }
}
