package cn.edu.stu.max.cocovendor.databaseClass;

import android.net.wifi.ScanResult;

import org.litepal.annotation.Encrypt;
import org.litepal.crud.DataSupport;
import org.litepal.util.cipher.AESCrypt;

public class LocalInfo extends DataSupport{
    private long machine_id;
    private String ip;
    private String mac_address;
    private String server_ip;
    private String language;
    private String local_address;
    @Encrypt(algorithm = AES)
    private String default_password;
    @Encrypt(algorithm = AES)
    private String login_password;
    private int version;
    private String tel_number;
    private int ad_rules;

    public long getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(long machine_id) {
        this.machine_id = machine_id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac_address() {
        return mac_address;
    }

    public void setMac_address(String mac_address) {
        this.mac_address = mac_address;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLocal_address() {
        return local_address;
    }

    public void setLocal_address(String local_address) {
        this.local_address = local_address;
    }

    public String getDefault_password() {
        return default_password;
    }

    public void setDefault_password(String default_password) {
        this.default_password = default_password;
    }

    public String getLogin_password() {
        return login_password;
    }

    public void setLogin_password(String login_password) {
        this.login_password = login_password;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getTel_number() {
        return tel_number;
    }

    public void setTel_number(String tel_number) {
        this.tel_number = tel_number;
    }

    public int getAd_rules() {
        return ad_rules;
    }

    public void setAd_rules(int ad_rules) {
        this.ad_rules = ad_rules;
    }
}
