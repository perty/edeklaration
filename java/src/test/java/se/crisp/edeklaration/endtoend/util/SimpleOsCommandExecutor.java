package se.crisp.edeklaration.endtoend.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleOsCommandExecutor {

    private String workDir;
    private List<String> command;
    private List<String> output = new ArrayList<String>();

    public SimpleOsCommandExecutor(String workDir, List<String> command) {
        this.workDir = workDir;
        this.command = command;
        command.set(0, workDir + System.getProperty("file.separator") + command.get(0));
    }

    public int run() {
        try {
            ProcessBuilder pb = new ProcessBuilder();
            pb.directory(new File(workDir));
            pb.command(command);
            pb.redirectErrorStream(true);
            Process p = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String l;
            while ((l = reader.readLine()) != null) {
                output.add(l);
            }
            return p.waitFor();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getOutput() {
        return output;
    }
}