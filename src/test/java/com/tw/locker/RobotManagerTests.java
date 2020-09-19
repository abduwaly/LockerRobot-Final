package com.tw.locker;

import com.tw.locker.enums.BagSize;
import com.tw.locker.enums.LockerType;
import com.tw.locker.exceptions.NoStorageException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RobotManagerTests {

    private static final String TEST_BAG_1 = "testBag1";
    private static final String TEST_LOCKER_1 = "testLocker1";

    @Test
    void should_save_bag_successfully_and_return_ticket_given_small_bag_and_S_locker() {
        Bag bag = new Bag(TEST_BAG_1, BagSize.SMALL);

        Locker locker = new Locker(TEST_LOCKER_1, LockerType.S, 1);
        List<Locker> lockers = new ArrayList<>();
        lockers.add(locker);
        LockerRobotManager manager = new LockerRobotManager(lockers);

        Ticket actual = manager.saveBag(bag);

        assertNotNull(actual);
        assertEquals(actual.getBagId(), TEST_BAG_1);
        assertEquals(actual.getLockerId(), TEST_LOCKER_1);
    }

    @Test
    void should_return_no_storage_error_given_small_bag_and_S_locker_without_capacity(){
        Locker locker = new Locker(TEST_LOCKER_1, LockerType.S, 1);
        List<Locker> lockers = new ArrayList<>();
        lockers.add(locker);
        LockerRobotManager manager = new LockerRobotManager(lockers);
        manager.saveBag(new Bag("tempBagId", BagSize.SMALL));

        Bag bag = new Bag(TEST_BAG_1, BagSize.SMALL);
        assertThrows(NoStorageException.class, ()-> manager.saveBag(bag));
    }

}
