package com.kingsmen.finsights.ui.transactions;

import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.MPPointF;
import com.kingsmen.finsights.R;
import com.kingsmen.finsights.adapters.OfferAdapter;
import com.kingsmen.finsights.adapters.TransactionAdapter;
import com.kingsmen.finsights.dao.Transaction;
import com.kingsmen.finsights.values.TransactionList;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TransactionsFragment extends Fragment implements OnChartValueSelectedListener, AdapterView.OnItemSelectedListener {
    private String TAG = "TransactionsFragment";

    private TransactionsViewModel transactionsViewModel;
    Spinner transactionTypeSpinner;
    Spinner transactionDateSpinner;
    RecyclerView transactionHistoryRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private BarChart chart;

    private final RectF onValueSelectedRectF = new RectF();

    private View view;

    private String selectedType;
    private String selectedDate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        transactionsViewModel =
                ViewModelProviders.of(this).get(TransactionsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_transactions, container, false);
        // final TextView textView = root.findViewById(R.id.text_transactions);
        transactionsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        // find views
        transactionTypeSpinner = (Spinner) root.findViewById(R.id.transactionTypeSpinner);
        transactionDateSpinner = (Spinner) root.findViewById(R.id.transactionDateSpinner);
        transactionTypeSpinner.setOnItemSelectedListener(this);
        transactionDateSpinner.setOnItemSelectedListener(this);
        chart = root.findViewById(R.id.transactionBarChart);

        transactionHistoryRecyclerView = root.findViewById(R.id.transactionHistoryRecyclerView);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        transactionHistoryRecyclerView.setLayoutManager(layoutManager);


        // initialize values, layouts, etc.
        this.initSpinners();
        this.initBarChart();
        this.setData(3, 4);

        TransactionList transactions = new TransactionList();
        try {
            transactions.init();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Transaction> transactionHistory = transactions.getTransactions();
        mAdapter = new TransactionAdapter(getContext(), transactionHistory);
        transactionHistoryRecyclerView.setAdapter(mAdapter);

        this.view = root;
        return root;
    }

    private void initBarChart() {
        chart.setOnChartValueSelectedListener(this);

        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);

        chart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        chart.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        chart.setPinchZoom(false);

        chart.setDrawGridBackground(false);
        // chart.setDrawYLabels(false);

//        IAxisValueFormatter xAxisFormatter = new DayAxisValueFormatter(chart);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setTypeface(tfLight);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(7);
//        xAxis.setValueFormatter(xAxisFormatter);

//        IAxisValueFormatter custom = new MyAxisValueFormatter();

        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
        leftAxis.setLabelCount(8, false);
//        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setDrawGridLines(false);
//        rightAxis.setTypeface(tfLight);
        rightAxis.setLabelCount(8, false);
//        rightAxis.setValueFormatter(custom);
        rightAxis.setSpaceTop(15f);
        rightAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setForm(Legend.LegendForm.SQUARE);
        l.setFormSize(9f);
        l.setTextSize(11f);
        l.setXEntrySpace(4f);

//        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);
//        mv.setChartView(chart); // For bounds control
//        chart.setMarker(mv); // Set the marker to the chart

        // setting data
//        seekBarY.setProgress(50);
//        seekBarX.setProgress(12);
    }

    private void setData(int count, float range) {

        float start = 1f;

        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = (int) start; i < start + count; i++) {
            float val = (float) (Math.random() * (range + 1));

            if (Math.random() * 100 < 25) {
                values.add(new BarEntry(i, val));
            } else {
                values.add(new BarEntry(i, val));
            }
        }

        BarDataSet set1;

        if (chart.getData() != null &&
                chart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) chart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            chart.getData().notifyDataChanged();
            chart.notifyDataSetChanged();

        } else {
            set1 = new BarDataSet(values, "The year 2017");

            set1.setDrawIcons(false);

            int startColor1 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
            int startColor2 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_light);
            int startColor3 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_light);
            int startColor4 = ContextCompat.getColor(getContext(), android.R.color.holo_green_light);
            int startColor5 = ContextCompat.getColor(getContext(), android.R.color.holo_red_light);
            int endColor1 = ContextCompat.getColor(getContext(), android.R.color.holo_blue_dark);
            int endColor2 = ContextCompat.getColor(getContext(), android.R.color.holo_purple);
            int endColor3 = ContextCompat.getColor(getContext(), android.R.color.holo_green_dark);
            int endColor4 = ContextCompat.getColor(getContext(), android.R.color.holo_red_dark);
            int endColor5 = ContextCompat.getColor(getContext(), android.R.color.holo_orange_dark);

//            List<Fill> gradientFills = new ArrayList<>();
//            gradientFills.add(new Fill(startColor1, endColor1));
//            gradientFills.add(new Fill(startColor2, endColor2));
//            gradientFills.add(new Fill(startColor3, endColor3));
//            gradientFills.add(new Fill(startColor4, endColor4));
//            gradientFills.add(new Fill(startColor5, endColor5));

//            set1.setFills(gradientFills);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            data.setValueTextSize(10f);
//            data.setValueTypeface(tfLight);
            data.setBarWidth(0.9f);

            chart.setData(data);
        }
    }

    public void initSpinners() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.transactionTypeArray, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.transactionDateArray, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        transactionTypeSpinner.setAdapter(adapter);
        transactionDateSpinner.setAdapter(adapter2);
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null)
            return;

        RectF bounds = onValueSelectedRectF;
        chart.getBarBounds((BarEntry) e, bounds);
        MPPointF position = chart.getPosition(e, YAxis.AxisDependency.LEFT);

        Log.i("bounds", bounds.toString());
        Log.i("position", position.toString());

        Log.i("x-index",
                "low: " + chart.getLowestVisibleX() + ", high: "
                        + chart.getHighestVisibleX());

        MPPointF.recycleInstance(position);
    }

    @Override
    public void onNothingSelected() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.transactionTypeSpinner) {
            selectedType = (String) parent.getItemAtPosition(position);
        } else if (parent.getId() == R.id.transactionDateSpinner) {
            selectedDate = (String) parent.getItemAtPosition(position);
        }
        // TODO: fetch data according to filter
        this.updateBarChart(selectedType, selectedDate);
    }

    // TODO: update bar chart dynamically on spinner value change
    private void updateBarChart(String selectedType, String selectedDate) {
        return;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}