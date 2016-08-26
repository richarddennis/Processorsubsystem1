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

class Source extends Sim_entity {

    private final Sim_port out;
    private final Sim_negexp_obj delay;

    Source(String name, double mean) {
        
        super(name);
        out = new Sim_port("Out");
        add_port(out);
        
        // Create the source's distribution and add it
        delay = new Sim_negexp_obj("Delay", mean);
        add_generator(delay);
    }

    //Sends 100 commands to the CPU
    @Override
    public void body() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Body from source called");
            sim_schedule(out, 0.0, 0);
            // Sample the distribution
            sim_pause(delay.sample());
        }
    }
}
