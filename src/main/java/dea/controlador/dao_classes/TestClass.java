/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dea.controlador.dao_classes;

import org.springframework.stereotype.Service;

/**
 *
 * @author Doppler
 */
@Service
public class TestClass {
    private String sms;

    public TestClass() {
        sms="holas";
    }
    public String getSms(){
        return sms;
    }
}
