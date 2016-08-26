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

public class ProcessorSubsystem1 {

    public static void main(String[] args) {

        Sim_system.initialise();

        //(String name, double mean, double var)
        Source source = new Source("Source", 150.45);      
        Sink processor = new Sink("Processor", 110.5, 90.5);
        Disk disk1 = new Disk("Disk1", 130.0, 65.0);
        Disk disk2 = new Disk("Disk2", 350.5, 200.5);

        Sim_system.link_ports("Source", "Out", "Processor", "In");
        Sim_system.link_ports("Processor", "Out1", "Disk1", "In");
        Sim_system.link_ports("Processor", "Out2", "Disk2", "In");

        Sim_system.run();
    }
}
