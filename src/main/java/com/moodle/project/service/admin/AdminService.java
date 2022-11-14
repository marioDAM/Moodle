package com.moodle.project.service.admin;
import com.moodle.project.entity.models.Admin;
import com.moodle.project.repository.admin.AdminRepository;
import org.springframework.stereotype.Service;
import java.util.List;@Service
public class AdminService { private final AdminRepository adminRepository;public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }


    public List<Admin> getAdmins() {
        return adminRepository.findAll();
    }
}
