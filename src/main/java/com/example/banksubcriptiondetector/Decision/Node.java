package com.example.banksubcriptiondetector.Decision;

import java.util.HashMap;
import java.util.Map;

public class Node {
    String attribute,decision;
    Map<String,Node> childNodes =new HashMap<>();
    boolean leaf;
}
