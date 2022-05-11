package dev.nexus.Potlukk;


import dev.nexus.repos.PotluckRepo;
import dev.nexus.entities.Potluck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PotluckRepoTests {

    static Potluck testPotluck = null;

    @Autowired
    private PotluckRepo potluckRepo;

    @Test
    @Order(1)
    public void createPotluck(){
        Potluck potluck = new Potluck(0, "First Potluck", 24000, 1);
        potluckRepo.save(potluck);
        testPotluck = potluck;
        System.out.println(potluck);
        Assertions.assertNotEquals(0, potluck.getId());
    }

    @Test
    @Order(2)
    public void getAllPotlucks(){
        List<Potluck> potlucks = this.potluckRepo.findAll();
    }

    @Test
    @Order(3)
    public void getPotluckById(){
        Optional<Potluck> possiblePotluck = this.potluckRepo.findById(2);
        if(possiblePotluck.isPresent()){
            Potluck potluck = possiblePotluck.get();
            System.out.println(potluck);
        }else{
            System.out.println("No potluck returned.");
        }
    }

    @Test
    @Order(4)
    public void findPotluckByCreator(){
        List<Potluck> potluckList = this.potluckRepo.findPotluckByCreator(1);
        System.out.println(potluckList);
    }


    @Test
    @Order(5)
    public void deletePotluck(){
        this.potluckRepo.deleteById(3);
    }
}