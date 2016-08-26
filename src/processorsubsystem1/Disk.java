/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processorsubsystem1;

/**
 *
 * @author mad_r
 */
import eduni.simjava.*;
// Import the distributions
import eduni.simjava.distributions.*;

class Disk extends Sim_entity {

    private Sim_port in;
    private Sim_normal_obj delay;

    Disk(String name, double mean, double var) {
        super(name);

        in = new Sim_port("In");
        add_port(in);

        //Happens twice - one for HDD1 one for HDD2
        System.out.println("Create the disk's distribution and add it");

        // Create the disk's distribution and add it
        // Happens twice (1 per hdd)
        System.out.println(mean);
        System.out.println(var);
        delay = new Sim_normal_obj("Delay", mean, var);
        add_generator(delay);
        
    }

    @Override
    //Gets called multiple times (100 times due to the simulation lasting 100 rounds) (102?)-bug
    public void body() {
        while (Sim_system.running()) {
           System.out.println("New sim event (Disk)");
            Sim_event e = new Sim_event();
            sim_get_next(e);
            
//            System.out.println(e);

            sim_process(delay.sample());
            sim_completed(e);
        }
    }
}
