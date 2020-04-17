package com.example.paypassworddemo.pay;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;


import com.example.paypassworddemo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author fanbaolong
 *         <p>
 *         弹出主管授权弹框
 */
public class PassValitationPopwindow extends PopupWindow implements
        OnClickListener, AdapterView.OnItemClickListener {

    private Context mContext;

    private Stack<Integer> mNumberStack;//保存输入的数字
    private List<TextView> mNumberViewList;//保存密码显示的几个text
    private final static int NUMBER_BUTTON_DELETE = 11; //删除键
    private final static int NUMBER_BUTTON_ZERO = 10;//0号按键
    private final static int NUMBER_BUTTON_CLEAR = 9;//清除按键
    private final static int NUMBER_COUNT = 6;

    private TextView mNumber1TextView;
    private TextView mNumber2TextView;
    private TextView mNumber3TextView;
    private TextView mNumber4TextView;
    private TextView mNumber5TextView;
    private TextView mNumber6TextView;
    //输入支付密码
    LinearLayout po_rl_top;
    //支付密码
    TextView pop_tv_zhifumima;
    //忘记密码
    private TextView pop_tv_fotgetpwd;
    //银行卡号
    private TextView pop_cardnum;
    //关闭
    private ImageView pop_close;
    private ScrollGridView mNumbersGridView; // 密码gridView
    private InputPwdNumberAdapter mNumberAdapter;    // 数字adapter
    private boolean mIsPassword = true;
    private final static String PASSWORD_NUMBER_SYMBOL = "●";

    private int index = 0;//标识从提现过来的

    private OnInputNumberCodeCallback mCallback; // 返回结果的回调

    private View mMenuView;
    private View view;

    public PassValitationPopwindow() {
    }

    /**
     * @param mContext
     * @param view      设置位置时候需要有个view值
     * @param mCallback 返回输入的6位验证密码
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("InflateParams")
    public PassValitationPopwindow(Context mContext, View view, int index, OnInputNumberCodeCallback mCallback) {
        super(mContext);
        this.mContext = mContext;
        this.mCallback = mCallback;
        this.view = view;
        this.index = index;
        mNumberStack = new Stack<>();
        mNumberViewList = new ArrayList<>();
        mNumberAdapter = new InputPwdNumberAdapter(mContext);

        initView();
        if (index == 1) {
            pop_cardnum.setVisibility(View.GONE);
            po_rl_top.setVisibility(View.GONE);
            pop_tv_zhifumima.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 初始化View
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.pop_view, null);
        //输入支付密码
        po_rl_top = mMenuView.findViewById(R.id.po_rl_top);
        //支付密码
        pop_tv_zhifumima = mMenuView.findViewById(R.id.pop_tv_zhifumima);
//		mMenuView = LayoutInflater.from(mContext).inflate(R.layout.pop_view, null);


        mNumber1TextView = mMenuView.findViewById(R.id.number_1_textView);
        mNumber2TextView = mMenuView.findViewById(R.id.number_2_textView);
        mNumber3TextView = mMenuView.findViewById(R.id.number_3_textView);
        mNumber4TextView = mMenuView.findViewById(R.id.number_4_textView);
        mNumber5TextView = mMenuView.findViewById(R.id.number_5_textView);
        mNumber6TextView = mMenuView.findViewById(R.id.number_6_textView);
        pop_cardnum = mMenuView.findViewById(R.id.pop_cardnum);
        pop_close = mMenuView.findViewById(R.id.pop_close);
        mNumberViewList.add(mNumber1TextView);
        mNumberViewList.add(mNumber2TextView);
        mNumberViewList.add(mNumber3TextView);
        mNumberViewList.add(mNumber4TextView);
        mNumberViewList.add(mNumber5TextView);
        mNumberViewList.add(mNumber6TextView);

        mNumbersGridView = mMenuView.findViewById(R.id.numbers_gridView);
        mNumbersGridView.setAdapter(mNumberAdapter);
        mNumbersGridView.setOnItemClickListener(this);
        pop_close.setOnClickListener(this);
        //设置SelectPicPopupWindow的View
        this.setContentView(mMenuView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.__picker_mystyle);
        //设置SelectPicPopupWindow弹出窗体的背景
         this.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        //设置popup的位置
        this.showAtLocation(view,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });
    }

    /**
     * @param mContext
     * @param view      设置位置时候需要有个view值
     * @param mCallback 返回输入的6位验证密码
     */
    @SuppressWarnings("deprecation")
    @SuppressLint("InflateParams")
    public PassValitationPopwindow(Context mContext, View view, OnInputNumberCodeCallback mCallback) {
        super(mContext);
        this.mContext = mContext;
        this.mCallback = mCallback;
        this.view = view;

        mNumberStack = new Stack<>();
        mNumberViewList = new ArrayList<>();
        mNumberAdapter = new InputPwdNumberAdapter(mContext);

        initView();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pop_close:
                dismiss();
                break;

            default:
                break;
        }
    }

    /**
     * 数字按键的响应时间
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if (position == NUMBER_BUTTON_CLEAR) {
            clearnNumber();
            return;
        }
        if (position == NUMBER_BUTTON_DELETE) {
            deleteNumber();
        } else {
            if (position == NUMBER_BUTTON_ZERO) {
                mNumberStack.push(0);
            } else {
                mNumberStack.push(++position);
            }
        }
        refreshNumberViews(mNumberStack);
        //input 6 numbers complete
        if (mNumberStack.size() == NUMBER_COUNT) {
            StringBuilder codeBuilder = new StringBuilder();
            for (int number : mNumberStack) {
                codeBuilder.append(number);
            }
//            MineWithdrawalsActivity.Type = true;
//            MineWithdrawalsActivity.Path= codeBuilder.toString();
            mCallback.onSuccess(codeBuilder.toString());
            dismiss();
        }

    }

//    /**
//     * 验证密码，这里直接写在本地验证了
//     *
//     * @param code
//     */
//    private void validation(String code) {
//
//        if ("123456".equals(code)) {
//            mCallback.onSuccess();
//            dismiss();
//        } else {
//            clearnNumber();
//            new ToastPopupwindow(mContext, view, "验证失败,请重新验证");
//        }
//
//    }

    /**
     * 返回输出的结果
     */
    public interface OnInputNumberCodeCallback {
        void onSuccess(String code);
    }

    /**
     * 清空mNumberStack的内容并刷新密码格
     */
    public void clearnNumber() {
        mNumberStack.clear();
        refreshNumberViews(mNumberStack);
    }

    /**
     * 删除密码位数
     */
    public void deleteNumber() {
        if (mNumberStack.empty() || mNumberStack.size() > NUMBER_COUNT) {
            return;
        }
        mNumberStack.pop();
    }


    /**
     * 刷新输入框显示
     *
     * @param mNumberStack
     */
    public void refreshNumberViews(Stack<Integer> mNumberStack) {

        for (int i = 0, size = mNumberViewList.size(); i < size; i++) {
            int numSize = mNumberStack.size();
            if (i < numSize) {
                if (mIsPassword) {
                    mNumberViewList.get(i).setText(PASSWORD_NUMBER_SYMBOL);
                } else {
                    mNumberViewList.get(i).setText(String.valueOf(mNumberStack.get(i)));
                }
            } else {
                mNumberViewList.get(i).setText("");
            }
        }

    }


}
