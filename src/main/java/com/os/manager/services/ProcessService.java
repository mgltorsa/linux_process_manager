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
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return processes;
    }

	public String deleteProcess(UserProcess userProcess) {
        Runtime runtime = Runtime.getRuntime();
        String[] command = new String[] { "/bin/sh", "kill -9 "+userProcess.getPid() };
        String info = null;
        try {
            Process process = runtime.exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            
            while((info=br.readLine())!=null &&  !info.isEmpty()){
                info+= br.readLine();
            }
            return info;
        } catch (Exception e) {
            e.printStackTrace();
            info="505 - error";
        }

        return info;
	}

}