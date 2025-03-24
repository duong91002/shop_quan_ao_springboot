/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.shopquanao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shopquanao.enums.SettingKeyEnum;
import com.example.shopquanao.models.Setting;

/**
 *
 * @author haidu
 */
@Repository
public interface SettingRepository extends JpaRepository<Setting, SettingKeyEnum>{
	Optional<Setting> findByKey(SettingKeyEnum key);
}
