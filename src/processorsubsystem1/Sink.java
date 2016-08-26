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
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

class Sink extends Sim_entity {

    private Sim_port in;
    private Sim_port out1, out2;
    private Sim_normal_obj delay;
    private Sim_random_obj prob;

    Sink(String name, double mean, double var) {
        super(name);

        in = new Sim_port("In");
        out1 = new Sim_port("Out1");
        out2 = new Sim_port("Out2");

        add_port(in);
        add_port(out1);
        add_port(out2);

// Create the processor's distribution and probability generator and add them
        delay = new Sim_normal_obj("Delay", mean, var);
        prob = new Sim_random_obj("Probability");
        add_generator(delay);
        add_generator(prob);

    }

    public void body() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("output_test_run.txt", "UTF-8");
            System.out.println("Created and opening the text file");
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Sink.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (Sim_system.running()) {

            Sim_event e = new Sim_event();
            sim_get_next(e);

            // Sample the distribution
            sim_process(delay.sample());
            sim_completed(e);

            // Get the next probability
            double p = prob.sample();
            
            //60% of the time disk 1 should be used 
            //P = Probability
            if (p < 0.60) {
                //sim_schedule(Sim_port sm_prt, double d, int i)
                sim_schedule(out1, 0.0, 1);
                
                writer.println("sim_schedule(out1, 0.0, 1);");
                System.out.println("sim_schedule(out1, 0.0, 1");
                
            } else {
                sim_schedule(out2, 0.0, 1);
                writer.println("sim_schedule(out2, 0.0, 1);");
                System.out.println("sim_schedule(out2, 0.0, 1");

            }

        }
        writer.close();
        System.out.println("Closed the text file");
    }
}
