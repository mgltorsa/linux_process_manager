package com.os.manager.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.os.manager.model.UserProcess;

import org.springframework.stereotype.Service;

/**
 * InnerProcessService
 */
@Service
public class ProcessService {

    public List<UserProcess> getProcesses() {

        List<UserProcess> processes = new ArrayList<UserProcess>();
        Runtime runtime = Runtime.getRuntime();
        String command = "ps aux";
        try {

            Process process = runtime.exec(command);
            process.waitFor();

            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));

            processes = getProcesses(br);
            
            process.destroy();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        return processes;
    }

    private List<UserProcess> getProcesses(BufferedReader br) {
        List<UserProcess> processes = new ArrayList<UserProcess>();

        String line = null;

        try {
            int commandIndex =  br.readLine().indexOf("COMMAND");
           
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                
                UserProcess userProcess = parseUserProcess(line,commandIndex);
                processes.add(userProcess);
                
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processes;
    }

    private UserProcess parseUserProcess(String line, int commandIndex) {
        String[] info = line.split("\\s+");
        
        UserProcess userProcess = new UserProcess();        
        userProcess.setUser(info[0]);
        userProcess.setPid(info[1]);
        userProcess.setCpu(info[2]);
        userProcess.setMem(info[3]);
        userProcess.setVsz(info[4]);
        userProcess.setRss(info[5]);
        userProcess.setTty(info[6]);
        userProcess.setStat(info[7]);
        userProcess.setStart(info[8]);
        userProcess.setTime(info[9]);

        String command = "";
        for(int i=commandIndex;i<line.length();i++){
            command+=line.charAt(i);
        }
        userProcess.setCommand(command);
        return userProcess;
    }

    public String deleteProcess(String userProcess) {
        Runtime runtime = Runtime.getRuntime();
        String command = "kill -9 " + userProcess ;
        String info = "Response:\n";
        try {
            Process process = runtime.exec(command);
            process.waitFor();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                info += line;
            }
            
            int exitValue = process.exitValue();
            if(exitValue==0) {
            	info+="successed action";
            }else {
            	info+="unsuccessed action";
            }
            process.destroy();
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            info = "505 - error";
        }

        return info;
    }

}