/*
 * SonarQube PMD Plugin
 * Copyright (C) 2012 ${owner}
 * sonarqube@googlegroups.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package cn.m2home.xml;

import java.util.ArrayList;
import java.util.List;

public class PmdRuleset {

  private String description;

  private List<PmdRule> rules = new ArrayList<>();

  public PmdRuleset() {
    // no description
  }

  public PmdRuleset(String description) {
    this.description = description;
  }

  public List<PmdRule> getPmdRules() {
    return rules;
  }

  public void setRules(List<PmdRule> rules) {
    this.rules = rules;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void addRule(PmdRule rule) {
    rules.add(rule);
  }

}
